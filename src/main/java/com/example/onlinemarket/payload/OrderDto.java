package com.example.onlinemarket.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    @NotNull
    private Integer basketId;
    private boolean accepted;
}
