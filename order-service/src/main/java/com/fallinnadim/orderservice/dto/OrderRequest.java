package com.fallinnadim.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest (
        @NotBlank(message = "sku_code is mandatory")
        String sku_code,
        @NotNull(message = "price field is mandatory")
        @Positive(message = "price must be positive")
        BigDecimal price,
        @NotNull(message = "quantity field is mandatory")
        @Positive(message = "price must be positive")
        Integer quantity) {
}
