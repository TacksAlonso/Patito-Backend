package com.enterprise.patito.inventory.services;

import com.enterprise.patito.inventory.dto.ProductWithStockDTO;
import com.enterprise.patito.inventory.entity.Products;
import com.enterprise.patito.inventory.entity.Stock;
import com.enterprise.patito.inventory.repository.ProductsRepository;
import com.enterprise.patito.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServices {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    StockRepository stockRepository;

    public Products createProducts(Products product){
        return productsRepository.save(product);
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public Optional<Products> getProductsByID(Long id){
        return productsRepository.findById(id);
    }

    public Products updateProducts(Long id, Products productUpdate){
        return productsRepository.findById(id).map(products -> {
            products.setName(productUpdate.getName());
            products.setPrice(productUpdate.getPrice());
            return productsRepository.save(products);
        }).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    public Page<ProductWithStockDTO>  getProductsByName(String name, Pageable pageable) {
        Page<Products> productsPage = productsRepository.findByNameContaining(name, pageable);
        List<ProductWithStockDTO> productsWithStock = productsPage.getContent().stream().map(product -> {
            int stock = stockRepository.findByProductId(product.getId()).getQuantity();
            boolean available = stock > 0;
            return new ProductWithStockDTO(product.getId(), product.getName(), product.getPrice(), stock, available);
        }).collect(Collectors.toList());
        return new PageImpl<>(productsWithStock, pageable, productsPage.getTotalElements());
    }

    public void deleteProducts(Long id) {
        productsRepository.deleteById(id);
    }

}
