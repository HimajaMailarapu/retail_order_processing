package com.interview.retail_order_processing.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.retail_order_processing.config.ApplicationConfig;
import com.interview.retail_order_processing.repository.OrderDetailsRepository;
import com.interview.retail_order_processing.models.OrderDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer extends KafkaProducerBase {

    private KafkaTemplate<String, String> kafkaTemplate;

    private ApplicationConfig applicationConfig;

    private ObjectMapper objectMapper;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @KafkaListener(
            topics = "#{'${spring.kafka.template.default-topic}'}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public OrderDetails send(OrderDetails orderDTO) {
        String message = serialize(orderDTO);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.sendDefault(String.valueOf(orderDTO.getOrderId()), message);

        OrderDetails orderDetails;
        try {
            orderDetails = objectMapper.readValue(listenableFuture.get().getProducerRecord().value(), OrderDetails.class);
            orderDetailsRepository.postOrderDetails(orderDTO.getOrderId(), applicationConfig.getOrderPlaced());
        } catch (Exception e) {
            log.error("Exception encountered when producing message to kafka.", e);
            throw new RuntimeException();
        }
        log.info("published message to kafka for order: {}", orderDTO.getOrderId());
        return orderDetails;
    }

}
