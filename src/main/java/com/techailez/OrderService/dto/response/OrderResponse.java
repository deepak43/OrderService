package com.techailez.OrderService.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techailez.OrderService.type.OrderStatus;
import com.techailez.OrderService.type.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("total_amount")
    private Long totalAmount;

    @JsonProperty("order_status")
    private OrderStatus orderStatus;

    @JsonProperty("order_date")
    private Instant orderDate;

    @JsonProperty("product_details")
    private ProductDetails productDetails;

    @JsonProperty("payment_details")
    private PaymentDetails paymentDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductDetails {

        @JsonProperty("product_id")
        private Long id;

        @JsonProperty("product_name")
        private String name;

        @JsonProperty("product_price")
        private Long price;

        @JsonProperty("product_quantity")
        private Long quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentDetails {

        @JsonProperty("payment_id")
        private Long paymentId;

        @JsonProperty("payment_status")
        private String status;

        @JsonProperty("payment_mode")
        private PaymentMode paymentMode;

        @JsonProperty("amount")
        private Long amount;

        @JsonProperty("payment_date")
        private Instant paymentDate;

    }
}
