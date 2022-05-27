package com.interview.retail_order_processing.models;

public class ResponseModel {

    private String message;
    private Integer orderId;

    public ResponseModel(String message, Integer orderId) {
        this.message = message;
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOrderDetails() {
        return orderId;
    }

    public void setOrderDetails(Integer orderId) {
        this.orderId = orderId;
    }
}
