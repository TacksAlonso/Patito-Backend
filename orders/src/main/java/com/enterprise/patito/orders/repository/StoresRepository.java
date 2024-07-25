package com.enterprise.patito.orders.repository;

import com.enterprise.patito.orders.entity.Stores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepository extends JpaRepository<Stores,Long> {
}
