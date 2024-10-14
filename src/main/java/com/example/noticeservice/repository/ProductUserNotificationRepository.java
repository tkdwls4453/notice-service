package com.example.noticeservice.repository;


import com.example.noticeservice.domain.Product;
import com.example.noticeservice.domain.NotificationStatus;
import com.example.noticeservice.domain.ProductUserNotification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

public interface ProductUserNotificationRepository extends JpaRepository<ProductUserNotification, Long> {

    @Query("""
           select pun
           from ProductUserNotification pun
           where pun.product = :product and pun.status in  :statuses
           """
    )
    List<ProductUserNotification> findByProductAndInStatusOrderByCreated(
        @Param(value = "product") Product product,
        @Param(value = "statuses") List<NotificationStatus> statuses
    );

    @Async
    @Transactional
    @Modifying
    @Query("""
           update ProductUserNotification pun
           set pun.status = :status
           where pun.id in :ids 
           """
    )
    void updateStatus(
        @Param(value = "ids")List<Long> ids,
        @Param(value = "status") NotificationStatus status
    );
}
