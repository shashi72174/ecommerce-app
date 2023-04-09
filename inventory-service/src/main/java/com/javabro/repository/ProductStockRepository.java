package com.javabro.repository;

import com.javabro.model.Product;
import com.javabro.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStockRepository  extends JpaRepository<ProductStock, Long> {
    public Optional<ProductStock> findByProduct(Product product);
}
