package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Integer> {
    List<FavouriteProduct> findAllByUserId(Integer userId);
    boolean existsByUserIdAndProductId(Integer userId, Integer productId);
    void deleteByUserIdAndProductId(Integer userId, Integer productId);
}
