package com.javabro.service;

import com.javabro.dto.OrderDTO;
import com.javabro.dto.OrderLineItemDTO;
import com.javabro.events.*;
import com.javabro.model.Order;
import com.javabro.model.OrderLineItem;
import com.javabro.repository.OrderLineItemRepository;
import com.javabro.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<Long, OrderCreatedEvent> orderCreatedEventKafkaTemplate;

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    @Autowired
    private KafkaTemplate<Long, OrderPaymentEvent> orderPaymentEventKafkaTemplate;

    @Autowired
    private KafkaTemplate<Long, OrderShipmentEvent> orderShipmentEventKafkaTemplate;


    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderedDate(new Date());
        order.setOrderStatus("CREATED");
        order.setUserId(order.getUserId());
        order = orderRepository.save(order);            //yuou will get orderId - 1
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setOrderId(order.getId());            //1
        orderCreatedEvent.setOrderedDate(new Date());           //curr date
        final Order orderFinal = order;
        Set<OrderLineItemDTO> orderLineItemDTOSet = new HashSet();
        Set<OrderLineItemDTO> lineItemDTOSet = orderDTO.getOrderLineItems().stream().map(orderLineItemDTO -> {
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setOrder(orderFinal);
            orderLineItem.setOrderLineItemDate(new Date());
            orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
            orderLineItem.setProductId(orderLineItemDTO.getProductId());
            orderLineItem = orderLineItemRepository.save(orderLineItem);
            orderLineItemDTO.setId(orderLineItem.getId());
            return orderLineItemDTO;
        }).collect(Collectors.toSet());
        orderCreatedEvent.setOrderLineItems(lineItemDTOSet);
        orderCreatedEventKafkaTemplate.send("order-inventory-topic", orderCreatedEvent);
        return orderDTO;
    }


    @KafkaListener(topics = "inventory-orderlineitem-topic", groupId = "inventory-orderlineitem-event-group")
    public void consumeInventoryOrderLineItemTopicForOrder(InventoryOrderLineItemEvent inventoryOrderLineItemEvent) {
        Order order = orderRepository.findById(inventoryOrderLineItemEvent.getOrderId()).get();
        if(inventoryOrderLineItemEvent.getInventoryStatus().equals("OUT_OF_STOCK")) {
            order.setOrderStatus("FAILED");
            orderRepository.save(order);
        }else {
            if(order.getOrderStatus().equals("CREATED")) {
                OrderPaymentEvent orderPaymentEvent = new OrderPaymentEvent();
                orderPaymentEvent.setOrderId(inventoryOrderLineItemEvent.getOrderId());
                orderPaymentEvent.setUserId(order.getUserId());
                orderPaymentEvent.setAmount(inventoryOrderLineItemEvent.getFinalAmount());
                orderPaymentEventKafkaTemplate.send("order-payment-topic", orderPaymentEvent);
            }
        }
    }

    @KafkaListener(topics = "payment-order-topic", groupId = "payment-processed-event-group")
    public void consumePaymentOrderTopic(PaymentProcessedEvent paymentProcessedEvent) {
        if(paymentProcessedEvent.getPaymentStatus().equals("SUCCESS")){
            OrderShipmentEvent orderShipmentEvent = new OrderShipmentEvent();
            orderShipmentEvent.setOrderId(paymentProcessedEvent.getOrderId());
            orderShipmentEvent.setUserId(paymentProcessedEvent.getUserId());
            orderShipmentEvent.setDeliveryAddress("some address will go here");
            Order order = new Order();
            order.setId(paymentProcessedEvent.getOrderId());
            order.setOrderStatus("CONFIRMED");
            orderRepository.save(order);
            orderShipmentEvent.setOrderLineItemDTOSet(orderLineItemRepository.findByOrder(order).stream().map(orderLineItem -> {
                OrderLineItemDTO orderLineItemDTO = new OrderLineItemDTO();
                orderLineItemDTO.setProductId(orderLineItem.getProductId());
                orderLineItemDTO.setQuantity(orderLineItem.getQuantity());
                return orderLineItemDTO;
            }).collect(Collectors.toSet()));
            orderShipmentEventKafkaTemplate.send("order-shipment-topic", orderShipmentEvent);
        }else {
            Order order = orderRepository.findById(paymentProcessedEvent.getOrderId()).get();
            order.setOrderStatus("FAILED");
            orderRepository.save(order);


        }
    }
}