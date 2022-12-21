package com.techailez.OrderService.service;

import com.techailez.OrderService.dto.request.OrderRequest;
import com.techailez.OrderService.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);

    OrderResponse getOrderDetailsById(Long id);
}
