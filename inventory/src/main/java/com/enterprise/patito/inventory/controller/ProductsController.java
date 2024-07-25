package com.enterprise.patito.inventory.controller;

import com.enterprise.patito.inventory.dto.ProductWithStockDTO;
import com.enterprise.patito.inventory.entity.Products;
import com.enterprise.patito.inventory.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/products")
public class ProductsController {

    @Autowired
    ProductsServices productsServices;

    @PostMapping
    public ResponseEntity<Products> createProducts(@RequestBody Products Products) {
        Products newProducts = productsServices.createProducts(Products);
        return ResponseEntity.status(201).body(newProducts);
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(productsServices.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable Long id) {
        Optional<Products> product = productsServices.getProductsByID(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductWithStockDTO>> getProductsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page, // Default page is 0
            @RequestParam(defaultValue = "10") int size // Default page size is 10
    ) {
        int pageSize = Math.min(size, 50);
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductWithStockDTO> productsPage = productsServices.getProductsByName(name, pageable);
        return ResponseEntity.ok(productsPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productsServices.deleteProducts(id);
        return ResponseEntity.ok("Product deleted");
    }
}
