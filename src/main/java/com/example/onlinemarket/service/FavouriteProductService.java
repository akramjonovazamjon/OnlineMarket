package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.FavouriteProduct;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.FavouriteProductDto;
import com.example.onlinemarket.repository.FavouriteProductRepository;
import com.example.onlinemarket.repository.ProductRepository;
import com.example.onlinemarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteProductService {
    private final FavouriteProductRepository favouriteProductRepository;
    private final ProductRepository productRepository;


    public FavouriteProductService(FavouriteProductRepository favouriteProductRepository, ProductRepository productRepository) {
        this.favouriteProductRepository = favouriteProductRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getUserFavouriteProducts(Integer userId) {
        List<Integer> productsId = favouriteProductRepository
                .findAllByUserId(userId).stream()
                .map(FavouriteProduct::getProductId).toList();
        return productRepository.findAllByIdIn(productsId);
    }

    public ApiResponse addFavouriteProduct(FavouriteProductDto favouriteProductDto) {
        boolean exists = favouriteProductRepository.existsByUserIdAndProductId(favouriteProductDto.getUserId(),
                favouriteProductDto.getProductId());
        if (exists) {
            return new ApiResponse("Favourite product exist", false);
        }
        FavouriteProduct favouriteProduct = FavouriteProduct.builder()
                .userId(favouriteProductDto.getUserId())
                .productId(favouriteProductDto.getProductId())
                .build();
        favouriteProductRepository.save(favouriteProduct);
        return new ApiResponse("Favourite product added", true);
    }

    public ApiResponse deleteFavouriteProductById(Integer id) {
        try {
            favouriteProductRepository.deleteById(id);
            return new ApiResponse("Favourite product deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Some error arised", false);
        }
    }

    public ApiResponse deleteFavouriteProductByUserAndProductId(Integer userId, Integer productId) {
        try {

            Optional<FavouriteProduct> optionalFavouriteProduct = favouriteProductRepository.findByProductIdAndUserId(productId, userId);
            if (optionalFavouriteProduct.isEmpty()) {
                return new ApiResponse("Some error", false);
            }

            favouriteProductRepository.deleteById(optionalFavouriteProduct.get().getId());
            return new ApiResponse("Favourite product deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Favourite product not deleted", false);
        }
    }
}
