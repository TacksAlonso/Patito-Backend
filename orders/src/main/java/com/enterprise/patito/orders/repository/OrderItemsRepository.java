package com.enterprise.patito.orders.repository;

import com.enterprise.patito.orders.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
    List<OrderItems> findAllByOrderId(Long orderId);
}
