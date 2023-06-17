package com.example.onlinemarket.entity;

import com.example.onlinemarket.controller.vm.FavouriteProductVm;
import com.example.onlinemarket.dto.FavouriteProductDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "favourite_products")
public class FavouriteProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer productId;

    public static FavouriteProduct of(FavouriteProductDto dto, Integer userId) {
        return FavouriteProduct.builder()
                .userId(userId)
                .productId(dto.productId())
                .build();
    }

    public FavouriteProductVm from() {
        return new FavouriteProductVm(id, userId, productId);
    }
}
