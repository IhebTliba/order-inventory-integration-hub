package com.integrationhub.orderservice.dto;

import com.integrationhub.orderservice.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderResponse {
    private Long id;
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String currency;
    private OrderStatus status;
    private LocalDateTime createdAt;
}