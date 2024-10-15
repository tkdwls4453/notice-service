package com.example.noticeservice.notification.productNotification;

import com.example.noticeservice.notification.NotificationStatus;
import com.example.noticeservice.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductNotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Integer restockRound;

    private NotificationStatus status;
    private Long lastUserId;

    @Builder
    private ProductNotificationHistory(Long productId, Integer restockRound, Long lastUserId, NotificationStatus status) {
        this.productId = productId;
        this.restockRound = restockRound;
        this.lastUserId = lastUserId;
        this.status = status;
    }

    public void cancel() {
        status = NotificationStatus.CANCELED_BY_SOLD_OUT;
    }

    public boolean isCanceled() {
        return status == NotificationStatus.CANCELED_BY_SOLD_OUT;
    }

    public void updateLastUserId(Long userId){
        this.lastUserId = userId;
    }

    public void complete() {
        this.status = NotificationStatus.COMPLETED;
    }
}
