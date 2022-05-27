package com.interview.retail_order_processing.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private String orderPlaced = "PLACED";
    private String orderProcessed = "PROCESSED";
    private String apiKey = "1234";

    public String getOrderPlaced() {
        return orderPlaced;
    }

    public String getOrderProcessed() {
        return orderProcessed;
    }

    public String getApiKey() {
        return apiKey;
    }
}
