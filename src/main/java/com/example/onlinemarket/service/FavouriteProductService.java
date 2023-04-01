package com.example.onlinemarket.service;

import com.example.onlinemarket.controller.vm.FavouriteProductVm;
import com.example.onlinemarket.entity.FavouriteProduct;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.dto.FavouriteProductDto;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.exception.favourite_product.FavouriteProductExistException;
import com.example.onlinemarket.repository.FavouriteProductRepository;
import com.example.onlinemarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteProductService {
    private final FavouriteProductRepository favouriteProductRepository;
    private final ProductRepository productRepository;


    public List<Product> getUserFavouriteProducts() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Integer> productsId = favouriteProductRepository
                .findAllByUserId(user.getId()).stream()
                .map(FavouriteProduct::getProductId).toList();
        return productRepository.findAllByIdIn(productsId);
    }


    public FavouriteProductVm create(FavouriteProductDto dto) {
        boolean exists = favouriteProductRepository.existsByUserIdAndProductId(dto.userId(), dto.productId());
        if (exists) {
            throw new FavouriteProductExistException();
        }
        FavouriteProduct favouriteProduct = FavouriteProduct.of(dto);
        return favouriteProductRepository.save(favouriteProduct).from();
    }

    public void deleteFavouriteProductById(Integer id) {
        favouriteProductRepository.deleteById(id);
    }

    public void deleteFavouriteProductByUserAndProductId(Integer productId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        favouriteProductRepository.deleteByUserIdAndProductId(user.getId(), productId);
    }
}
