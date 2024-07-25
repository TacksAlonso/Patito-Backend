package com.enterprise.patito.orders.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ORDER_ITEMS")
@EqualsAndHashCode(exclude = "order")
@ToString(exclude = "order")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders order;

    private String hawa;
    private Integer quantity;
    private Double price;
    private Double discount;
}
