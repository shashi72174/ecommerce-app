package com.javabro.events;

import com.javabro.dto.OrderLineItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Set<OrderLineItemDTO> orderLineItems;
    private Date orderedDate;
}
