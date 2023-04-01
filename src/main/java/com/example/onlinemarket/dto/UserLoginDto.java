package com.example.onlinemarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(
        @NotBlank(message = "error.invalid.email.not_blank") @Email String email,
        @NotBlank(message = "error.invalid.password.not_blank") String password) {
}
