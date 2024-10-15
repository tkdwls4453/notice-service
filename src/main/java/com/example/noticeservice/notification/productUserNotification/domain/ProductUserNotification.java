package com.example.noticeservice.notification.productUserNotification.domain;

import com.example.noticeservice.common.BaseEntity;
import com.example.noticeservice.notification.NotificationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductUserNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long userId;

    boolean isActive;

    @Builder
    private ProductUserNotification(Long id, Long productId, Long userId, boolean isActive, NotificationStatus status) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.isActive = isActive;
    }

    @Override
    public int compareTo(ProductUserNotification other) {
        return other.getUpdatedAt().compareTo(this.getUpdatedAt());
    }
}
