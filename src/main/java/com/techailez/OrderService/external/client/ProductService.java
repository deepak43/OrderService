package com.techailez.OrderService.external.client;

import com.techailez.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductService {

    @PutMapping("/reducequantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable Long id, @RequestParam Long quantity);

    default void fallback(Exception e) {
        throw new CustomException("Product service not available", "SERVICE_NOT_AVAILABLE", 500);
    }
}
