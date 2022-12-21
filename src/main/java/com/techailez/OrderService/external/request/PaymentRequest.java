package com.techailez.OrderService.external.request;

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
public class PaymentRequest {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("reference_number")
    private String referenceNumber;

    @JsonProperty("payment_mode")
    private PaymentMode paymentMode;
}
