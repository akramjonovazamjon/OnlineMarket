package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must not be empty")
    private String name;
}
