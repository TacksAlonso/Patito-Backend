package com.enterprise.patito.customers.repository;

import com.enterprise.patito.customers.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    List<Customers> findByNameOrLastnameOrPhoneOrEmail(String name, String lastname, String phone, String email);
}
