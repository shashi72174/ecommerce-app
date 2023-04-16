package com.javabro.events;

import com.javabro.dto.OrderLineItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderShipmentEvent {
    private Long userId;
    private Long orderId;
    private String deliveryAddress;
    private Set<OrderLineItemDTO> orderLineItemDTOSet;
}
