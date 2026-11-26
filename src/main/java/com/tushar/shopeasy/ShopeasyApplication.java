package com.tushar.shopeasy;

import com.tushar.shopeasy.config.ExchangeRateService;
import com.tushar.shopeasy.order.OrderService;
import com.tushar.shopeasy.order.RefundService;
import com.tushar.shopeasy.payment.RazorpayGateway;
import com.tushar.shopeasy.payment.StripGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class ShopeasyApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;

	@Autowired
	private RefundService refundService;

	@Autowired
	private ExchangeRateService exchangeRateService;

	public static void main(String[] args) {
		SpringApplication.run(ShopeasyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		orderService.shop("Rahul", "Shoes");
		orderService.shop("Priya", "Watch");
		double amount = exchangeRateService.toInr("USD",5);
		orderService.placeOrder("Razor-1" , amount,"upi");
		orderService.placeOrder("Razor-2" , 5.00,"strip");
		try {
			orderService.placeOrder("Razor-3" , 50.00,"paypal");
        } catch (IllegalArgumentException e) {
			System.out.println("Order Failed : " + e.getMessage());
		}



		refundService.refund("Strip-1" , 50.00);
	}
}
