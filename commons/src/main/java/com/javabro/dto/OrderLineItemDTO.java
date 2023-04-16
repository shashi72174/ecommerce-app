package com.javabro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String orderLineItemStatus;
}
