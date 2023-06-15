package com.example.onlinemarket.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "basket_items")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer productId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;

    public static BasketItem of(Integer productId, Integer quantity, Double price) {
        return BasketItem.builder()
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
