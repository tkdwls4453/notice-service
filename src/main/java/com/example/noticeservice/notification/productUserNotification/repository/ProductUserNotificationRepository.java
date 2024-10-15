package com.example.noticeservice.notification.productUserNotification.repository;

import com.example.noticeservice.notification.productUserNotification.domain.ProductUserNotification;
import com.example.noticeservice.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductUserNotificationRepository extends JpaRepository<ProductUserNotification, Long> {

    @Query("""
           select pun
           from ProductUserNotification pun
           where pun.productId = :productId and pun.isActive = true
           """
    )
    List<ProductUserNotification> findByProductAndIsActive(@Param(value = "productId")Long productId);

}
