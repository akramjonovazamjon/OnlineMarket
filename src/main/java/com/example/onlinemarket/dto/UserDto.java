package com.example.onlinemarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserDto(
        @NotBlank(message = "error.invalid.first_name.not_blank") String firstName,
        @NotBlank(message = "error.invalid.last_name.not_blank") String lastName,
        @NotBlank(message = "error.invalid.email.not_blank") @Email String email,
        @NotBlank(message = "error.invalid.password.not_blank") String password) {
}
