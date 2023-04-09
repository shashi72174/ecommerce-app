package com.javabro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockDTO {
    private Long id;
    private Long productId;
    private Long numStocks;
}
