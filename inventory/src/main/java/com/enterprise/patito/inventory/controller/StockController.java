package com.enterprise.patito.inventory.controller;

import com.enterprise.patito.inventory.entity.Stock;
import com.enterprise.patito.inventory.services.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("v1/stock")
public class StockController {

    @Autowired
    StockServices stockServices;

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock Stock){
        Stock newStock = stockServices.createStock(Stock);
        return ResponseEntity.status(201).body(newStock);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        return ResponseEntity.ok(stockServices.getAllStock());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Stock> getStockByProductsId(@PathVariable Long id) {
        Stock Stocks = stockServices.getStockByProductId(id);
        return ResponseEntity.ok(Stocks);
    }

    @PutMapping
    public ResponseEntity<Stock> updateStock(@RequestParam Long productId, @RequestParam int quantity) {
        Stock updatedStock = stockServices.removeStock(productId, quantity);
        return ResponseEntity.ok(updatedStock);
    }
}
