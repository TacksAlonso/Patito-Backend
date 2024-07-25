package com.enterprise.patito.inventory.repository;

import com.enterprise.patito.inventory.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products,Long> {
    List<Products> findByNameContainingIgnoreCase(String name);
    Page<Products> findByNameContaining(String name, Pageable pageable);
}
