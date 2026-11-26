package com.tushar.shopeasy.order;

import com.tushar.shopeasy.notification.NotificationService;
import com.tushar.shopeasy.payment.PaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    private final PaymentGateway paymentGateway;
    private final NotificationService notificationService;

    public RefundService(@Qualifier("strip") PaymentGateway paymentGateway, NotificationService notificationService) {
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
    }

    public void refund(String id, double amount) {
        paymentGateway.refund(id,amount);
        notificationService.notify(id);
    }
}
