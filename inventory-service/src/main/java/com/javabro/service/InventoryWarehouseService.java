package com.javabro.service;

import com.javabro.events.InventoryOrderLineItemEvent;
import com.javabro.events.OrderCreatedEvent;
import com.javabro.model.Product;
import com.javabro.model.ProductStock;
import com.javabro.repository.ProductRepository;
import com.javabro.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryWarehouseService {

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    ProductRepository productRepository;
    public com.javabro.events.dto.ProductStockDTO updateProductStock(com.javabro.events.dto.ProductStockDTO productStockDTO) {
        Product product = productRepository.findById(productStockDTO.getProductId()).orElseThrow(RuntimeException::new);
        Optional<ProductStock> productStockOptional = productStockRepository.findByProduct(product);
        ProductStock productStock = null;
        if(!productStockOptional.isPresent()) {
            productStock = new ProductStock();
            productStock.setProduct(product);
        }else
            productStock = productStockOptional.get();
        productStock.setNumStocks(productStockDTO.getNumStocks());
        productStock = productStockRepository.save(productStock);
        productStockDTO.setId(productStock.getId());
        return productStockDTO;
    }

    public void consumeOrderInventoryCheck(OrderCreatedEvent orderCreatedEvent){
        double sum = 0d;
        orderCreatedEvent.getOrderLineItems().stream().map(orderLineItemDTO -> {
            Product product = new Product();
            product.setId(orderLineItemDTO.getProductId());
            ProductStock productStock = productStockRepository.findByProduct(product).get();
            if(productStock.getNumStocks()>orderLineItemDTO.getQuantity()) {
                product = productRepository.findById(productStock.getProduct().getId()).get();
                product.getProductPrice();
            }
            return null;
        });
    }
}