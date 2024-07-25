package com.enterprise.patito.inventory.repository;

import com.enterprise.patito.inventory.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductId(Long productId);
}
