package com.tushar.shopeasy.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    private Map<String,Double> rates;

    @PostConstruct
    public void loadRates() {
//        System.out.println("Rates Loading");
        rates = new HashMap<>();
        rates.put("USD", 83.5);
        rates.put("EUR", 90.2);
        System.out.println("Rates Loaded");
    }

    @PreDestroy
    public void clearRates() {
        rates.clear();
        System.out.println("Rates cache clear");
    }

    public double toInr(String currency, double amount) {
        double exchangerate = rates.get(currency);
        return amount*exchangerate;
    }
}
