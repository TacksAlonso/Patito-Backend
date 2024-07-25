package com.enterprise.patito.customers.services;

import com.enterprise.patito.customers.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enterprise.patito.customers.entity.Customers;

import java.util.List;
import java.util.Optional;


@Service
public class CustomersServices {

    @Autowired
    CustomersRepository customersRepository;

    public Customers createCustomers(Customers customers){
        return customersRepository.save(customers);
    }

    public List<Customers> getAllCustomers(){
        return customersRepository.findAll();
    }

    public Optional<Customers> getCustomersByID(Long id){
        return customersRepository.findById(id);
    }


    public Customers updateClient(Long id, Customers customersUpdate){
        return customersRepository.findById(id).map(customers -> {
            customers.setName(customersUpdate.getName());
            customers.setAddress(customersUpdate.getAddress());
            customers.setPhone(customersUpdate.getPhone());
            customers.setEmail(customersUpdate.getEmail());
            return customersRepository.save(customers);
        }).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }

    public List<Customers> searchCustomers(String name, String lastname, String phone, String email) {
        return customersRepository.findByNameOrLastnameOrPhoneOrEmail(name, lastname, phone, email);
    }

    public void deleteCustomer(Long id) {
        customersRepository.deleteById(id);
    }

}
