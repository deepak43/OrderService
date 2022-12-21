package com.techailez.OrderService.controller;

import com.techailez.OrderService.dao.Order;
import com.techailez.OrderService.dto.request.OrderRequest;
import com.techailez.OrderService.dto.response.OrderResponse;
import com.techailez.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest request) {
        OrderResponse saved = service.placeOrder(request);
        return new ResponseEntity<>(saved.getOrderId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderDetailsById(@PathVariable Long id) {
        OrderResponse response = service.getOrderDetailsById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
