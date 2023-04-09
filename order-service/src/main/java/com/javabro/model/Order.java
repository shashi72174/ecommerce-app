package com.javabro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Date orderedDate;
    private Long userId;
    private String orderStatus;
    @OneToMany(mappedBy="order")
    private List<OrderLineItem> orderLineItems;
}
