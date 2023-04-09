package com.javabro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private String productDescription;
    private Double productPrice;
    @ManyToOne
    private ProductCategory productCategory;
}
