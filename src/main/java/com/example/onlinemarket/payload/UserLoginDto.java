package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @NotNull(message = "Email number must not be empty")
    private String email;
    @NotNull(message = "Password must not be empty")
    private String password;
}
