package com.integrationhub.order_service.event;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderCreatedEvent {
    private Long orderId;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String currency;
    private LocalDateTime createdAt;
}