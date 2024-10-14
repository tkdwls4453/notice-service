package com.example.noticeservice.notification;

import com.example.noticeservice.product.ProductUserNotification;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class NotificationManager {

    private PriorityBlockingQueue<ProductUserNotification> notificationPriorityQueue;

    public static NotificationManager getNotificationSender() {
        return new NotificationManager();
    }

    public NotificationManager() {
        this.notificationPriorityQueue = new PriorityBlockingQueue<>();
    }
    public void send(List<ProductUserNotification> productUserNotificationList) {
        productUserNotificationList.forEach(notificationPriorityQueue::offer);  // 큐에 추가
    }
    public boolean isEmpty() {
        return notificationPriorityQueue.isEmpty();
    }
    public ProductUserNotification poll() {
        return notificationPriorityQueue.poll();
    }
    public void manageAll(List<ProductUserNotification> productUserNotificationList) {
        productUserNotificationList.stream()
            .forEach(notificationPriorityQueue::offer);

    }
}
