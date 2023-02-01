package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @NotNull(message = "Name must not be empty")
    private String name;
    @NotNull(message = "Info must not be empty")
    private String info;
    @NotNull(message = "Price must not be empty")
    private Double price;
    @NotNull(message = "Quantity must not be empty")
    private Integer quantity;
    @NotNull(message = "Category id must not be empty")
    private Integer categoryId;
}
