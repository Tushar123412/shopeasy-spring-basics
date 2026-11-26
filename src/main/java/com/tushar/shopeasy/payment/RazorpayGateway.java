package com.tushar.shopeasy.payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("razorpay")
//@Primary
public class RazorpayGateway implements PaymentGateway{
    @Override
    public void pay(String orderId, double amount) {
        if(amount <= 0) {
            throw new IllegalStateException("Amount must be positive");
        }
        System.out.println("Paid " + amount + " for " + orderId + " via Razorpay");
    }

    @Override
    public void refund(String orderId, double amount) {
        System.out.println("Refund " + amount + " for " + orderId + " via Razorpay");
    }
}
