package com.example.noticeservice.notification.service;

import com.example.noticeservice.notification.NotificationManager;
import com.example.noticeservice.product.Product;
import com.example.noticeservice.product.repository.ProductUserNotificationRepository;
import com.example.noticeservice.notification.NotificationStatus;
import com.example.noticeservice.product.ProductUserNotification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final ProductUserNotificationRepository productUserNotificationRepository;
    public void createAndSend(Product product) {

        List<NotificationStatus> canceledStatuses = NotificationStatus.getCanceledStatuses();

        // 상품 - 유저 알림 조회 (정렬 X)
        List<ProductUserNotification> productUserNotificationList = productUserNotificationRepository.
            findByProductAndInStatusOrderByCreated(product, canceledStatuses);

        List<Long> ids = productUserNotificationList.stream()
            .map(ProductUserNotification::getId)
            .toList();

        // 유저 알림을 IN_PROGRESS 로 변경
        productUserNotificationRepository.updateStatus(ids, NotificationStatus.IN_PROGRESS);

        NotificationManager notificationManager = NotificationManager.getNotificationSender();
        notificationManager.manageAll(productUserNotificationList);
    }
}