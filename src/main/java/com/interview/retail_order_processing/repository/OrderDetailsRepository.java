package com.interview.retail_order_processing.repository;

import com.interview.retail_order_processing.domain.OrderDetailsEntity;
import com.interview.retail_order_processing.models.OrderDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.cassandra.repository.CassandraRepository;

@Repository
public interface OrderDetailsRepository extends CassandraRepository<OrderDetailsEntity, Integer> {

    @Query(value = "select orderId,orderStatus from orderDetails where orderId = :orderId")
    OrderDetails getOrderDetails(@Param("orderId") Integer orderId);

    @Modifying
    @Query("update orderDetails set orderStatus = orderStatus where orderId = :orderId")
    @Transactional
    OrderDetails postOrderDetails(@Param("orderId") Integer orderId, @Param("orderStatus") String orderStatus);
}
