package com.example.noticeservice.notification.productUserNotification.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 상품 + 유저별 알림 히스토리
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductUserNotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long userId;

    private Integer restockRound;
    private LocalDateTime shipmentDate;

    @Builder
    private ProductUserNotificationHistory(Long productId, Long userId, Integer restockRound, LocalDateTime shipmentDate) {
        this.productId = productId;
        this.userId = userId;
        this.restockRound = restockRound;
        this.shipmentDate = shipmentDate;
    }

    public void updateShipmentDate() {
        this.shipmentDate = LocalDateTime.now();
    }
}
