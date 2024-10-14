package com.example.noticeservice.product;

import com.example.noticeservice.common.BaseEntity;
import com.example.noticeservice.notification.NotificationStatus;
import com.example.noticeservice.common.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class ProductUserNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private NotificationStatus status;

    @Override
    public int compareTo(ProductUserNotification other) {
        return other.getCreatedAt().compareTo(this.getCreatedAt());
    }
}
