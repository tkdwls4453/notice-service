package com.example.noticeservice.notification.productUserNotification.domain;

import com.example.noticeservice.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ActiveNotification extends BaseEntity {

    private Long productId;
    private Long userId;
    private Integer restockRound;

    private boolean isLast;

    @Builder
    private ActiveNotification(Long productId, Long userId, Integer restockRound, boolean isLast) {
        this.productId = productId;
        this.userId = userId;
        this.restockRound = restockRound;
        this.isLast = isLast;
    }


    public void updateLast(){
        this.isLast = true;
    }

    @Override
    public int compareTo(ProductUserNotification other) {
        return other.getUpdatedAt().compareTo(this.getUpdatedAt());
    }
}
