package com.example.onlinemarket.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private Integer productId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;
}
