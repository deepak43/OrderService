package com.techailez.OrderService.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techailez.OrderService.type.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("total_amount")
    private Long totalAmount;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("payment_mode")
    private PaymentMode paymentMode;

}
