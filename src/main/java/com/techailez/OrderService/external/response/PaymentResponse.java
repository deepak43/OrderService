package com.techailez.OrderService.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techailez.OrderService.type.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

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

    @JsonProperty("order_id")
    private Long orderId;

}
