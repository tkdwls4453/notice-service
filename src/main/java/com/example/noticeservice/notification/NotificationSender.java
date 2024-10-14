package com.example.noticeservice.notification;

import com.example.noticeservice.product.ProductUserNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationSender {
    private final int MAX_SEND_COUNT = 500;
    private final NotificationManager notificationManager;

    // 주기적으로 메시지 처리 (1초마다)
    @Scheduled(fixedRate = 1000)
    public void processSend() {
        int curCnt = MAX_SEND_COUNT;
        while (!notificationManager.isEmpty() && curCnt > 0) {
            ProductUserNotification notification = notificationManager.poll();

            // 메시지 전송 로직

            curCnt--;
        }
    }
}
