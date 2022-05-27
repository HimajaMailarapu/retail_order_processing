package com.interview.retail_order_processing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "orderDetails")
public class OrderDetailsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_status")
    private String orderStatus;
}
