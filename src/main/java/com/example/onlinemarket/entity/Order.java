package com.example.onlinemarket.entity;

import com.example.onlinemarket.controller.vm.OrderVm;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Basket basket;
    private boolean accepted;

    public static Order of(Basket basket) {
        return Order.builder()
                .basket(basket)
                .build();
    }

    public OrderVm from() {
        return new OrderVm(id, basket, accepted);
    }
}
