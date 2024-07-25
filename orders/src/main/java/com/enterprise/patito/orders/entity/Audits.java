package com.enterprise.patito.orders.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "AUDITS")
public class Audits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Orders order;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_ip", nullable = false)
    private String userIp;
}