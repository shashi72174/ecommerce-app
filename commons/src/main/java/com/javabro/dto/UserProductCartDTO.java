package com.javabro.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserProductCartDTO {
    private Long id;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer quantity;
}
