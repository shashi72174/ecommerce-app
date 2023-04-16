package com.javabro.controllers;

import com.javabro.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/products")
    public ResponseEntity<List<com.javabro.events.dto.InventoryDTO>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<com.javabro.events.dto.InventoryDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<com.javabro.events.dto.InventoryDTO> saveProduct(@RequestBody com.javabro.events.dto.InventoryDTO inventoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.saveProduct(inventoryDTO));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<com.javabro.events.dto.InventoryDTO> updateProduct(@RequestBody com.javabro.events.dto.InventoryDTO inventoryDTO, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateProduct(inventoryDTO, id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        inventoryService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
