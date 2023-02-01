package com.example.onlinemarket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BasketItem> basketItems;
    @Column(nullable = false)
    private Double totalPrice;
    @OneToOne
    private User user;
}
