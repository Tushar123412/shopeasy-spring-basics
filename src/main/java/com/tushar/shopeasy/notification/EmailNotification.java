package com.tushar.shopeasy.notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notification.channel", havingValue = "email")
public class EmailNotification implements NotificationService{
    @Override
    public void notify(String orderId) {
        System.out.println("Email Notification send for Order Id : " + orderId);
    }
}
