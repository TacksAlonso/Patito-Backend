package com.enterprise.patito.orders.repository;

import com.enterprise.patito.orders.entity.Audits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditsRepository extends JpaRepository<Audits,Long> {
}
