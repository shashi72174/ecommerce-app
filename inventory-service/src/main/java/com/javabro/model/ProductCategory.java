package com.javabro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String categoryName;
    private String categoryDescription;
    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}
