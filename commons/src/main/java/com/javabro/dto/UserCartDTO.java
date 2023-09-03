package com.javabro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCartDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private Date addedOn;


}
