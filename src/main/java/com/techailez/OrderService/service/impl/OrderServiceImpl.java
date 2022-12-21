package com.techailez.OrderService.service.impl;

import com.techailez.OrderService.dao.Order;
import com.techailez.OrderService.dto.request.OrderRequest;
import com.techailez.OrderService.dto.response.OrderResponse;
import com.techailez.OrderService.exception.CustomException;
import com.techailez.OrderService.external.client.PaymentService;
import com.techailez.OrderService.external.client.ProductService;
import com.techailez.OrderService.external.request.PaymentRequest;
import com.techailez.OrderService.external.response.PaymentResponse;
import com.techailez.OrderService.external.response.ProductResponse;
import com.techailez.OrderService.repository.OrderRepository;
import com.techailez.OrderService.service.OrderService;
import com.techailez.OrderService.type.OrderStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        log.info("Checking for product quantity before placing order");

        productService.reduceQuantity(request.getProductId(), request.getQuantity());

        log.info("Quantity check passed. Placing the order, {}", request);

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .orderDate(Instant.now())
                .orderStatus(OrderStatus.CREATED)
                .amount(request.getTotalAmount())
                .build();

        repository.save(order);

        log.info("Order Placed successfully with order id : {}", order.getId());

        log.info("Calling Payment Service to complete the service");

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(request.getPaymentMode())
                .amount(order.getAmount())
                .build();

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment Successful. Changing order status to ACCEPTED");
            order.setOrderStatus(OrderStatus.ACCEPTED);
        } catch (Exception e) {
            log.info("Payment Failed. Changing order status to PAYMENT_FAILED");
            order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        }

        Order saved = repository.save(order);

        OrderResponse response = OrderResponse.builder()
                .orderId(saved.getId())
                .totalAmount(saved.getAmount())
                .orderStatus(saved.getOrderStatus())
                .build();

        return response;
    }

    @Override
    public OrderResponse getOrderDetailsById(Long id) {
        log.info("Getting order details for id : {}", id);
        Order order = repository.findById(id)
                .orElseThrow(()-> new CustomException("Order not found for provided id", "ORDER_NOT_FOUND", 404));

        log.info("Invoking product service to get product details for product id {}", order.getProductId());

        ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/products/"+order.getProductId(), ProductResponse.class);

        log.info("Getting payment details from payment service for order id : {}", order.getId());

        PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payments/order/"+order.getId(), PaymentResponse.class);

        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentDate(paymentResponse.getPaymentDate())
                .status(paymentResponse.getStatus())
                .amount(paymentResponse.getAmount())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .id(productResponse.getId())
                .name(productResponse.getName())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity())
                .build();

        OrderResponse response = OrderResponse.builder()
                .orderId(order.getId())
                .totalAmount(order.getAmount())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        return response;
    }
}
