package com.example.noticeservice.notification.productUserNotification.repository;

import com.example.noticeservice.notification.productUserNotification.domain.ProductUserNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUserNotificationHistoryRepository extends JpaRepository<ProductUserNotificationHistory, Long> {

}
