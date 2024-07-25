package com.enterprise.patito.orders.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "STORES")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
}