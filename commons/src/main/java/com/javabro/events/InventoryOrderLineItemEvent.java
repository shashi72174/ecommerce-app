package com.javabro.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryOrderLineItemEvent {
    private Long orderId;
    private Long orderLineItemId;
    private String inventoryStatus;
    private Double finalAmount;     //0 if any one of the line item is out of stock or else it will calculate and send us the final amount for respective orderId
}
