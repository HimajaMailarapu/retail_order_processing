package com.interview.retail_order_processing.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.retail_order_processing.config.ApplicationConfig;
import com.interview.retail_order_processing.repository.OrderDetailsRepository;
import com.interview.retail_order_processing.models.OrderDetails;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ApplicationConfig applicationConfig;

    @KafkaListener(
            topics = "#{'${spring.kafka.template.default-topic}'}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void processConsumerRecord(
            ConsumerRecord<String, String> record) {
        if (record.value() != null) {
            try {
                OrderDetails orderDTO = mapper.readValue(record.value(), OrderDetails.class);
                orderDetailsRepository.postOrderDetails(orderDTO.getOrderId(), applicationConfig.getOrderProcessed());

                logger.info(String.format("Processed the record for order: {} ",
                        orderDTO.getOrderId()));
            } catch (JsonProcessingException e) {
                logger.info(String.format("Error processing the record : {} ",
                        record.value()));
            }
        } else {
            logger.debug("Received Batch with no records to process");
        }
    }
}
