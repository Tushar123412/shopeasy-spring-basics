package com.tushar.shopeasy.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("strip")
@Qualifier("stripeGateway")
public class StripGateway implements PaymentGateway{
    @Override
    public void pay(String orderId, double amount) {
        if(amount <= 0) {
            throw new IllegalStateException("Amount must be positive");
        }
        System.out.println("Paid " + amount + " for " + orderId + " via Strip");
    }

    @Override
    public void refund(String orderId, double amount) {
        System.out.println("Refund " + amount + " for " + orderId + " via Strip");
    }
}
