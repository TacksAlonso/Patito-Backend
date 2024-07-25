package com.enterprise.patito.customers.controller;

import com.enterprise.patito.customers.entity.Customers;
import com.enterprise.patito.customers.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/customers")
public class CustomersController {

    @Autowired
    CustomersServices customersServices;

    @PostMapping
    public ResponseEntity<Customers> createCustomers(@RequestBody Customers customers){
        Customers newCustomers = customersServices.createCustomers(customers);
        return ResponseEntity.status(201).body(newCustomers);
    }

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        return ResponseEntity.ok(customersServices.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> getCustomersById(@PathVariable Long id) {
        Optional<Customers> product = customersServices.getCustomersByID(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customers>> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email) {

        List<Customers> customers = customersServices.searchCustomers(name, lastname, phone, email);
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        customersServices.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted");
    }

}
