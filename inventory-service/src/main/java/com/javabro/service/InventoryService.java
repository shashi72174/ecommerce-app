package com.javabro.service;

import com.javabro.model.Product;
import com.javabro.model.ProductCategory;
import com.javabro.repository.ProductCategoryRepository;
import com.javabro.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<com.javabro.events.dto.InventoryDTO> getAllProducts() {
        com.javabro.events.dto.InventoryDTO inventoryDTO = new com.javabro.events.dto.InventoryDTO();
        return productRepository.findAll().stream().map(product -> {
            BeanUtils.copyProperties(product, inventoryDTO);
            return inventoryDTO;
        }).collect(Collectors.toList());
    }

    public com.javabro.events.dto.InventoryDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        com.javabro.events.dto.InventoryDTO inventoryDTO = new com.javabro.events.dto.InventoryDTO();
        BeanUtils.copyProperties(product, inventoryDTO);
        return inventoryDTO;
    }

    public com.javabro.events.dto.InventoryDTO saveProduct(com.javabro.events.dto.InventoryDTO inventoryDTO) {
        Product product = new Product();
        ProductCategory productCategory = productCategoryRepository.findById(inventoryDTO.getProductCategoryId()).orElseThrow(RuntimeException::new);
        product.setProductCategory(productCategory);
        BeanUtils.copyProperties(inventoryDTO, product);
        product = productRepository.save(product);
        BeanUtils.copyProperties(product, inventoryDTO);
        return inventoryDTO;
    }

    public com.javabro.events.dto.InventoryDTO updateProduct(com.javabro.events.dto.InventoryDTO inventoryDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductCategory productCategory = productCategoryRepository.findById(inventoryDTO.getProductCategoryId()).orElseThrow(RuntimeException::new);
        BeanUtils.copyProperties(inventoryDTO, product);
        product.setId(id);
        product.setProductCategory(productCategory);
        product = productRepository.save(product);
        BeanUtils.copyProperties(product, inventoryDTO);
        return inventoryDTO;
    }

    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        productRepository.deleteById(id);
    }
}
