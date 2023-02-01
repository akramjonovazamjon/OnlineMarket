package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotNull(message = "First name must not be null")
    @NotBlank(message = "First name must not be empty")
    private String firstName;
    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be empty")
    private String lastName;
    @NotNull(message = "Phone number must not be null")
    @NotBlank(message = "Phone number must not be empty")
    private String phoneNumber;
    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be empty")
    private String password;
}
