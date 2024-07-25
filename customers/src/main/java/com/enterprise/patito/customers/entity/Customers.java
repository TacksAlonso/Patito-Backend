package com.enterprise.patito.customers.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "CUSTOMERS")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String address;
}
