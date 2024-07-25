package com.enterprise.patito.orders.controller;

import com.enterprise.patito.orders.entity.Stores;
import com.enterprise.patito.orders.services.StoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stores")
public class StoresController {
    @Autowired
    private StoresServices storesServices;

    @GetMapping
    public ResponseEntity<List<Stores>> getAllStores() {
        List<Stores> stores = storesServices.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stores> getStoreById(@PathVariable Long id) {
        Stores store = storesServices.getStoreById(id);
        return store != null ? ResponseEntity.ok(store) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Stores> createStore(@RequestBody Stores store) {
        Stores createdStore = storesServices.createStore(store);
        return ResponseEntity.ok(createdStore);
    }
}
