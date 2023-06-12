package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Integer> {
    Optional<BasketItem> findByProductId(Integer productId);
}
