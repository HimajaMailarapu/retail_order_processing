package com.interview.retail_order_processing.controller;

import com.interview.retail_order_processing.config.ApplicationConfig;
import com.interview.retail_order_processing.kafka.producer.KafkaProducer;
import com.interview.retail_order_processing.models.OrderDetails;
import com.interview.retail_order_processing.models.ResponseModel;
import com.interview.retail_order_processing.service.OrderDetailsService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderDetailsController {

    public OrderDetailsService orderDetailsService;

    private ApplicationConfig applicationConfig;

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "retail_order_processing/{order_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public OrderDetails getOrderDetails(@PathVariable("order_id") Integer orderId, @RequestHeader(value = "x-api-key", required = true) String apiKey) {
        if(apiKey != null && apiKey.equalsIgnoreCase(applicationConfig.getApiKey()))
            if (orderId != null)
                return orderDetailsService.getOrderGroupDetails(orderId);
        return null;
    }

    @RequestMapping(value = "retail_order_processing/{order_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseModel postOrderDetails(@Valid @RequestBody OrderDetails orderDTO, @PathVariable("order_id") Integer orderId) {
        if (orderId != null && orderDTO != null) {
            kafkaProducer.send(orderDTO);
            return new ResponseModel("Update Successfull", orderId);
        }
        return new ResponseModel("Unable to post the record to kafka topic", orderId);
    }
}
