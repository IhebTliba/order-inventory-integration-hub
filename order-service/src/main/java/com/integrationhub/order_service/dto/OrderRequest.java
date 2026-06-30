package com.integrationhub.order_service.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {

    @NotBlank(message = "Product code is required")
    private String productCode;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be positive")
    private BigDecimal unitPrice;

    @NotBlank
    @Pattern(regexp = "EUR|USD|GBP", message = "Currency must be EUR, USD or GBP")
    private String currency;
}
