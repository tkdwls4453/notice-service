package com.example.noticeservice.notification.productNotification.repository;

import com.example.noticeservice.notification.productNotification.ProductNotificationHistory;
import com.example.noticeservice.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductNotificationHistoryRepository extends JpaRepository<ProductNotificationHistory, Long> {

    ProductNotificationHistory findByProductId(Long productId);
}
