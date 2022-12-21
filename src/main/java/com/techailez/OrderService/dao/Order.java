package com.techailez.OrderService.dao;

import com.techailez.OrderService.type.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDER_PRODUCT_ID")
    private Long productId;

    @Column(name = "ORDER_QUANTITY")
    private Long quantity;

    @Column(name = "ORDER_AMOUNT")
    private Long amount;

    @Column(name = "ORDER_DATE")
    private Instant orderDate;

    @Column(name = "ORDER_STATUS")
    private OrderStatus orderStatus;
}
