package com.example.noticeservice.notification;

import com.example.noticeservice.notification.productUserNotification.domain.ActiveNotification;
import com.example.noticeservice.notification.productUserNotification.domain.ProductUserNotification;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {

    private PriorityBlockingQueue<ActiveNotification> notificationPriorityQueue;

    public static NotificationManager getNotificationSender() {
        return new NotificationManager();
    }

    public NotificationManager() {
        this.notificationPriorityQueue = new PriorityBlockingQueue<>();
    }
    public void send(List<ActiveNotification> productUserNotificationList) {
        productUserNotificationList.forEach(notificationPriorityQueue::offer);  // 큐에 추가
    }
    public boolean isEmpty() {
        return notificationPriorityQueue.isEmpty();
    }

    public ActiveNotification poll() {
        return notificationPriorityQueue.poll();
    }

    public void manageAll(List<ActiveNotification> notifications) {
        notifications.stream()
            .forEach(notificationPriorityQueue::offer);

    }
}
