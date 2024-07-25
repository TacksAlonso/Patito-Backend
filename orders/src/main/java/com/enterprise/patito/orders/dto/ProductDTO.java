package com.enterprise.patito.orders.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
}
