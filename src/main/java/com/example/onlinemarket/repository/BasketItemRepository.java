package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketItemRepository extends JpaRepository<BasketItem, Integer> {
    Optional<BasketItem> findByProductId(Integer product_id);
}
