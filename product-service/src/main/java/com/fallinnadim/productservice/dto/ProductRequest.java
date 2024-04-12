package com.fallinnadim.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        String id,
        @NotBlank(message = "name field is mandatory")
        String name,
        @NotBlank(message = "description field is mandatory")
        String description,
        @NotNull(message = "price field is mandatory")
        @Positive(message = "price must be positive")
        BigDecimal price) {
}
