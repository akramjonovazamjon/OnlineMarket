package com.example.onlinemarket.dto;

import com.example.onlinemarket.model.PermissionEnum;
import com.example.onlinemarket.model.RoleEnum;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record AdminRegisterDto(
        @NotBlank(message = "error.invalid.first_name.not_blank") String firstName,
        @NotBlank(message = "error.invalid.last_name.not_blank") String lastName,
        @NotBlank(message = "error.invalid.email.not_blank") String email,
        @NotBlank(message = "error.invalid.password.not_blank") String password,
        List<RoleEnum> roles,
        List<PermissionEnum> permissions) {
}
