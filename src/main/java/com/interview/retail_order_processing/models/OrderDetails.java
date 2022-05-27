package com.interview.retail_order_processing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetails {

    @JsonProperty("order_id")
    private Integer orderId;

    @JsonProperty("order_status")
    private String orderStatus;

    public OrderDetails() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
