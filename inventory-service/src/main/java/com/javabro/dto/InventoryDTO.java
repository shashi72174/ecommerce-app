package com.javabro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class InventoryDTO {
    private Long id;
    private String productName;
    private String productDescription;
    private Long productCategoryId;
    private Double productPrice;
}
