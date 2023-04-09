package com.javabro.controllers;

import com.javabro.dto.InventoryDTO;
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
    public ResponseEntity<List<InventoryDTO>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<InventoryDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<InventoryDTO> saveProduct(@RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.saveProduct(inventoryDTO));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<InventoryDTO> updateProduct(@RequestBody InventoryDTO inventoryDTO, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateProduct(inventoryDTO, id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        inventoryService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
