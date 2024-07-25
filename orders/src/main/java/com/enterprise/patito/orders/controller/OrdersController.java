package com.enterprise.patito.orders.controller;

import com.enterprise.patito.orders.dto.OrderDTO;
import com.enterprise.patito.orders.entity.Orders;
import com.enterprise.patito.orders.services.OrdersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrdersController {
    @Autowired
    private OrdersServices ordersServices;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllStores() {
        List<OrderDTO> orders = ordersServices.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getStoreById(@PathVariable Long id) {
        OrderDTO order = ordersServices.getOrderDTOById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order, HttpServletRequest request) {
        Orders createdOrder = ordersServices.createOrder(order);
        String location = String.format("/%d", createdOrder.getId());
        return ResponseEntity.created(URI.create(location)).body(createdOrder);
    }

    @PutMapping
    public ResponseEntity<Orders> updateOrder(@RequestParam Long id, @RequestParam String state) {
        try {
            Orders updatedOrder = ordersServices.updateOrder(id, state);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }
}
