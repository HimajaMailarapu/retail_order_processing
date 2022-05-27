package com.interview.retail_order_processing.service;

import com.interview.retail_order_processing.repository.OrderDetailsRepository;
import com.interview.retail_order_processing.models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetails getOrderGroupDetails(Integer orderId) {
        return orderDetailsRepository.getOrderDetails(orderId);

    }
}
