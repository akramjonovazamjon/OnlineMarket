package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketDto {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;
}
