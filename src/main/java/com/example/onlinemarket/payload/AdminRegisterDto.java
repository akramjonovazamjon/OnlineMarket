package com.example.onlinemarket.payload;

import com.example.onlinemarket.model.PermissionEnum;
import com.example.onlinemarket.model.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminRegisterDto {
    @NotNull(message = "First name must not be null")
    @NotBlank(message = "First name must not be empty")
    private String firstName;
    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be empty")
    private String lastName;
    @NotNull(message = "Email number must not be null")
    @NotBlank(message = "Email number must not be empty")
    @Email
    private String email;
    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be empty")
    private String password;
    private List<RoleEnum> roles;
    private List<PermissionEnum> permissions;
}
