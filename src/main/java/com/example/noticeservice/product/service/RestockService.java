package com.example.noticeservice.product.service;

import com.example.noticeservice.notification.NotificationStatus;
import com.example.noticeservice.notification.productNotification.ProductNotificationHistory;
import com.example.noticeservice.notification.productNotification.repository.ProductNotificationHistoryRepository;
import com.example.noticeservice.notification.productUserNotification.service.NotificationManageService;
import com.example.noticeservice.product.Product;
import com.example.noticeservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class RestockService {

    private final ProductRepository productRepository;
    private final NotificationManageService noticeService;
    private final ProductNotificationHistoryRepository productNotificationHistoryRepository;

    public void restock(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new IllegalArgumentException()
        );

        // 재고 업데이트
        product.restock(quantity);

        // 재고 히스토리
        ProductNotificationHistory productNotificationHistory = ProductNotificationHistory.builder()
            .productId(product.getId())
            .restockRound(product.getRestockRound())
            .status(NotificationStatus.IN_PROGRESS)
            .build();

        noticeService.createAndSend(productNotificationHistory);

        // 상품, 재고 히스토리 저장
        productNotificationHistoryRepository.save(productNotificationHistory);
    }

}
