package com.enterprise.patito.orders.services;

import com.enterprise.patito.orders.entity.Stores;
import com.enterprise.patito.orders.repository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoresServices {
    @Autowired
    private StoresRepository storeRepository;

    public List<Stores> getAllStores() {
        return storeRepository.findAll();
    }

    public Stores getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Stores createStore(Stores store) {
        return storeRepository.save(store);
    }
}
