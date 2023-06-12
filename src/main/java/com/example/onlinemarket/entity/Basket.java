package com.example.onlinemarket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BasketItem> basketItems;
    @Column(nullable = false)
    private Double totalPrice;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public static Basket of(List<BasketItem> basketItems, Double totalPrice, User user) {
        return Basket.builder()
                .basketItems(basketItems)
                .totalPrice(totalPrice)
                .user(user)
                .build();
    }
}
