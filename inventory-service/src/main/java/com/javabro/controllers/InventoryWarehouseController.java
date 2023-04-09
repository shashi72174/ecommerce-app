package com.javabro.controllers;

import com.javabro.dto.ProductStockDTO;
import com.javabro.service.InventoryWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class InventoryWarehouseController {
    @Autowired
    InventoryWarehouseService inventoryWarehouseService;

    @PostMapping("/updateProductStocks")
    public ProductStockDTO updateProductStocks(@RequestBody ProductStockDTO productStockDTO) {
        return inventoryWarehouseService.updateProductStock(productStockDTO);
    }
}
