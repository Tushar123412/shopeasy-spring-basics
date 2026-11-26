package com.tushar.shopeasy.order;

import com.tushar.shopeasy.notification.NotificationService;
import com.tushar.shopeasy.payment.PaymentGateway;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class OrderService {

//    private final PaymentGateway paymentGateway;
    private final NotificationService notificationService;

    private final Map<String,PaymentGateway> gateways;

//    private  ShoppingCart shoppingCart;

    private final ObjectProvider<ShoppingCart> cartProvider;


    private final Clock systemZone;

//    public OrderService(PaymentGateway paymentGateway, NotificationService notificationService) {
//        this.paymentGateway = paymentGateway;
//        this.notificationService = notificationService;
//    }


//    public OrderService(Map<String, PaymentGateway> gateways, NotificationService notificationService) {
//        this.gateways = gateways;
//        this.notificationService = notificationService;
//    }

//    public OrderService(NotificationService notificationService, Map<String, PaymentGateway> gateways, ShoppingCart shoppingCart) {
//        this.notificationService = notificationService;
//        this.gateways = gateways;
//        this.shoppingCart = shoppingCart;
//    }

    public OrderService(ObjectProvider<ShoppingCart> cartProvider, Map<String, PaymentGateway> gateways, NotificationService notificationService, Clock systemZone) {
        this.cartProvider = cartProvider;
        this.gateways = gateways;
        this.notificationService = notificationService;
        this.systemZone = systemZone;
    }


    //    public void placeOrder(String id, double amount) {
//        paymentGateway.pay(id, amount);
//        notificationService.notify(id);
//    }

    public void placeOrder(String id, double amount, String method) {
        PaymentGateway paymentGateway = gateways.get(method);

        if(paymentGateway == null) {
            throw new IllegalArgumentException("Unsupported Gateway : " + method);
        }

        paymentGateway.pay(id, amount);
        System.out.println("Order Placed at " + LocalDateTime.now(systemZone));
        notificationService.notify(id);
    }

    public void shop(String customer, String... items) {
        ShoppingCart shoppingCart = cartProvider.getObject();
        for(String item: items) {
            shoppingCart.addItem(item);
        }

        System.out.println(customer + " Cart : " + shoppingCart.getItems());

    }
}
