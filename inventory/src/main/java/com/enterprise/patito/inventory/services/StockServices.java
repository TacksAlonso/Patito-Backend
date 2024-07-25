package com.enterprise.patito.inventory.services;

import com.enterprise.patito.inventory.entity.Products;
import com.enterprise.patito.inventory.entity.Stock;
import com.enterprise.patito.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServices {
    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductsServices productsRepository;

    public Stock createStock(Stock stock) {
        Products product = productsRepository.getProductsByID(stock.getProductId()).orElse(null);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        stock.setProduct(product);
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Stock getStockByProductId(Long id) {
        return stockRepository.findByProductId(id);
    }

    public Stock removeStock(Long productId, int quantity) {
        Stock stock = stockRepository.findByProductId(productId);
        if (stock == null || stock.getQuantity() + quantity < 0) {
            throw new RuntimeException("Sin inventario");
        }
        stock.setQuantity(stock.getQuantity() + quantity);
        return stockRepository.save(stock);
    }
}
