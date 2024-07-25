package com.enterprise.patito.inventory.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = "product_id")
})
public class Stock {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @NotNull
    private Products product;

    @Column(name = "quantity")
    private int quantity;

}
