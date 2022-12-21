package com.techailez.OrderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductService {

    @PutMapping("/reducequantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable Long id, @RequestParam Long quantity);
}