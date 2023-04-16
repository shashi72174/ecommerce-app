package com.javabro.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderPaymentEvent {
    private Long userId;
    private Double amount;
    private Long orderId;
}
