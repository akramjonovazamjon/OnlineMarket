package com.example.onlinemarket.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(@NotBlank(message = "error.invalid.name.not_blank") String name) {
}
