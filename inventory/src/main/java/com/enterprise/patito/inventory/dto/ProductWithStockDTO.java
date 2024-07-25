package com.enterprise.patito.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWithStockDTO {
    private Long id;
    private String name;
    private double price;
    private int stock;
    private boolean available;
}
