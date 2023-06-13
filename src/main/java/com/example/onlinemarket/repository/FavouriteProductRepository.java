package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Integer> {
    List<FavouriteProduct> findAllByUserId(Integer userId);

    boolean existsByUserIdAndProductId(Integer userId, Integer productId);

    void deleteByUserIdAndProductId(Integer userId, Integer productId);
}
