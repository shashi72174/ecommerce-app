package com.javabro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStock {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    private Long numStocks;
}