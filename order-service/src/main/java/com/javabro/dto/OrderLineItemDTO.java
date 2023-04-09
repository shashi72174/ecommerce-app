package com.javabro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDTO {
    private Long productId;
    private Double amount;
    private Integer quantity;
}
