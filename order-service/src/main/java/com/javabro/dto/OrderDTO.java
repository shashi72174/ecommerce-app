package com.javabro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long userId;
    private Set<OrderLineItemDTO> orderLineItems;
    private Date orderedDate;
}
