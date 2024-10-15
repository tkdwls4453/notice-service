package com.example.noticeservice.notification.productUserNotification.service;

import com.example.noticeservice.notification.NotificationManager;
import com.example.noticeservice.notification.productNotification.ProductNotificationHistory;
import com.example.noticeservice.notification.productUserNotification.repository.ProductUserNotificationRepository;
import com.example.noticeservice.notification.productUserNotification.domain.ActiveNotification;
import com.example.noticeservice.notification.productUserNotification.domain.ProductUserNotification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationManageService {
    private final ProductUserNotificationRepository productUserNotificationRepository;
    public void createAndSend(ProductNotificationHistory productNotificationHistory) {

        // 상품 - 유저 알림 조회 (정렬 X , isActive = true)
        List<ProductUserNotification> productUserNotificationList = productUserNotificationRepository.
            findByProductAndIsActive(productNotificationHistory.getProductId());

        List<ActiveNotification> activeNotifications = productUserNotificationList.stream()
            .map(item -> ActiveNotification.builder()
                .productId(item.getProductId())
                .userId(item.getUserId())
                .restockRound(productNotificationHistory.getRestockRound())
                .isLast(false)
                .build())
            .collect(Collectors.toList());

        // 마지막 알림에 라스트 표기
        activeNotifications.get(activeNotifications.size() - 1).updateLast();

        // 가져온 알림을 우선순위 큐에서 관리 (notificationManager 는 싱글톤으로 관리)
        NotificationManager notificationManager = NotificationManager.getNotificationSender();
        notificationManager.manageAll(activeNotifications);
    }
}
