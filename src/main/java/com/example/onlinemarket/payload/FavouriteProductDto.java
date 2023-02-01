package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteProductDto {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer productId;
}
