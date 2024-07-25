package com.enterprise.patito.orders.services;

import com.enterprise.patito.orders.entity.OrderItems;
import com.enterprise.patito.orders.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServices {
    @Autowired
    private OrderItemsRepository orderItemRepository;

    public List<OrderItems> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItems getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItems createOrderItem(OrderItems orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItems> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }
}
