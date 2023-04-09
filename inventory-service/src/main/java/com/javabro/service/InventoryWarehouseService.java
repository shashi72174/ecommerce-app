package com.javabro.service;

import com.javabro.dto.ProductStockDTO;
import com.javabro.model.Product;
import com.javabro.model.ProductStock;
import com.javabro.repository.ProductRepository;
import com.javabro.repository.ProductStockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryWarehouseService {

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductRepository productRepository;
    public ProductStockDTO updateProductStock(ProductStockDTO productStockDTO) {
        Product product = productRepository.findById(productStockDTO.getProductId()).orElseThrow(RuntimeException::new);
        Optional<ProductStock> productStockOptional = productStockRepository.findByProduct(product);
        ProductStock productStock = null;
        if(!productStockOptional.isPresent()) {
            productStock = new ProductStock();
            productStock.setProduct(product);
        }
        productStock.setNumStocks(productStockDTO.getNumStocks());
        productStock = productStockRepository.save(productStock);
        productStockDTO.setId(productStock.getId());
        return productStockDTO;
    }
}