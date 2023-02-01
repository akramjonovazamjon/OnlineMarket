package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Integer> {
    List<FavouriteProduct> findAllByUserId(Integer user_id);
    Optional<FavouriteProduct> findByProductIdAndUserId(Integer productId, Integer userId);
    boolean existsByUserIdAndProductId(Integer userId, Integer productId);
}
