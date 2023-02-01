package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEditDto {
    private String name;
    private String info;
    private Double price;
    private Integer quantity;
}
