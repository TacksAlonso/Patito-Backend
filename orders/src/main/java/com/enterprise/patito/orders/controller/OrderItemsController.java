package com.enterprise.patito.orders.controller;

import com.enterprise.patito.orders.entity.OrderItems;
import com.enterprise.patito.orders.services.OrderItemsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orderitems")
public class OrderItemsController {
    @Autowired
    private OrderItemsServices orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        List<OrderItems> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Long id) {
        OrderItems orderItem = orderItemService.getOrderItemById(id);
        return orderItem != null ? ResponseEntity.ok(orderItem) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems orderItem) {
        OrderItems createdOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItems>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItems> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }
}
