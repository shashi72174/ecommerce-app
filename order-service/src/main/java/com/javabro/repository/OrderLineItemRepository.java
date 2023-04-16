package com.javabro.repository;

import com.javabro.model.Order;
import com.javabro.model.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
    public List<OrderLineItem> findByOrder(Order order);
}
