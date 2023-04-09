package com.javabro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(referencedColumnName = "id")
    private Order order;
    private Date orderLineItemDate;
    private Long productId;
    private Integer quantity;
    private Double amount;

}
