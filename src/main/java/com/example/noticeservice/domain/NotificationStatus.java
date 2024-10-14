package com.example.noticeservice.domain;


import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationStatus {

    IN_PROGRESS("발송 중"),
    CANCELED_BY_SOLD_OUT ("품절에 의한 발송 중단"),
    CANCELED_BY_ERROR ("예외에 의한 발송 중단"),
    COMPLETED("완료");


    private String message;

    public static List<NotificationStatus> getCanceledStatuses(){
        return List.of(CANCELED_BY_ERROR, CANCELED_BY_SOLD_OUT);
    }
}
