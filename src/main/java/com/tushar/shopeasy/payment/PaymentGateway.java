package com.tushar.shopeasy.payment;

public interface PaymentGateway {
    void pay(String orderId,double amount);
    void refund(String orderId,double amount);
}
