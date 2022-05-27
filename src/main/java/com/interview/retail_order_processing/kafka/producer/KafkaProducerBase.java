package com.interview.retail_order_processing.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.retail_order_processing.models.OrderDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.serializer.SerializerException;

@Component
@Slf4j
public class KafkaProducerBase {

    @Autowired
    private ObjectMapper mapper;

    String serialize(OrderDetails orderDTO) {
        try {
            return mapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            log.error("Exception serializing aggregate message: {}", orderDTO);
            throw new SerializerException("Exception serializing aggregate message");
        }
    }
}
