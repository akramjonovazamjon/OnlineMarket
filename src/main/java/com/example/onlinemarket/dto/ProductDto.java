package com.example.onlinemarket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
        @NotBlank(message = "error.invalid.name.not_blank") String name,
        String info,
        @NotNull(message = "error.invalid.price.not_null") Double price,
        @NotNull(message = "error.invalid.quantity.not_null") Integer quantity,
        @NotNull(message = "error.invalid.categoryId.not_null") Integer categoryId) {
}
