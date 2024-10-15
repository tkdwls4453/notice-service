package com.example.noticeservice.notification.productUserNotification.service;

import com.example.noticeservice.notification.NotificationManager;
import com.example.noticeservice.notification.productNotification.ProductNotificationHistory;
import com.example.noticeservice.notification.productNotification.repository.ProductNotificationHistoryRepository;
import com.example.noticeservice.notification.productUserNotification.domain.ActiveNotification;
import com.example.noticeservice.notification.productUserNotification.domain.ProductUserNotificationHistory;
import com.example.noticeservice.notification.productUserNotification.repository.ProductUserNotificationHistoryRepository;
import com.example.noticeservice.product.Product;
import com.example.noticeservice.product.repository.ProductRepository;
import com.google.common.util.concurrent.RateLimiter;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSendService {
    private final int MAX_SEND_COUNT = 500;
    private final NotificationManager notificationManager;
    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;
    private final ProductUserNotificationHistoryRepository productUserNotificationHistoryRepository;
    private final ProductRepository productRepository;

    private final RateLimiter rateLimiter = RateLimiter.create(500.0);


    @PostConstruct
    public void processSendNotificationContinuously(){
        while(true){
            processSend();
        }
    }
    public void processSend() {
        int curCnt = MAX_SEND_COUNT;
        List<ActiveNotification> outOfStockNotifications = new ArrayList<>(); // 재고부족으로 실패한 알림들 모임

        while (!notificationManager.isEmpty() && curCnt > 0) {
            ActiveNotification notification = notificationManager.poll();

            Long productId = notification.getProductId();
            ProductNotificationHistory productUserNotificationHistory = productNotificationHistoryRepository.findByProductId(productId);
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException());

            // 재고 부족시, 처리하지 않고 리스트에 모아 두었다가 나중에 처리한다.
            if(productUserNotificationHistory.isCanceled()){
                outOfStockNotifications.add(notification);
                continue;
            }

            // 재고 감소, 재고가 없으면 히스토리에 상태 변경 (CANCELED_BY_SOLD_OUT)
            product.decreaseStock();
            if (product.isOutOfStock()) {
                productUserNotificationHistory.cancel();
            }

            // 현재 회차의 마지막 알림이면, 히스토리 상태 변경 (COMPLETED)
            if (notification.isLast()) {
                productUserNotificationHistory.complete();
            }

            // 매번 히스토리의 마지막 유저 변경
            productUserNotificationHistory.updateLastUserId(notification.getUserId());
            curCnt--;

            // 실제 알림 보내는 부분 (히스토리 디비에 저장)
            rateLimiter.acquire();
            ProductUserNotificationHistory history = ProductUserNotificationHistory.builder().build();
            history.updateShipmentDate();
            productUserNotificationHistoryRepository.save(history);
        }

        notificationManager.manageAll(outOfStockNotifications);
        outOfStockNotifications.clear();
    }
}
