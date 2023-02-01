package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.entity.BasketItem;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.BasketDto;
import com.example.onlinemarket.repository.BasketItemRepository;
import com.example.onlinemarket.repository.BasketRepository;
import com.example.onlinemarket.repository.ProductRepository;
import com.example.onlinemarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BasketItemRepository basketItemRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository, UserRepository userRepository, BasketItemRepository basketItemRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.basketItemRepository = basketItemRepository;
    }

    public Basket getUserBasket(Integer userId) {
        return basketRepository.findByUserId(userId).orElse(null);
    }

    public ApiResponse addToBasket(BasketDto basketDto) {
        Optional<Basket> optionalUserBasket = basketRepository.findByUserId(basketDto.getUserId());
        if (optionalUserBasket.isEmpty()) {
            Optional<Product> optionalProduct = productRepository.findById(basketDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
            if (optionalProduct.isEmpty()) {
                return new ApiResponse("Product not found", false);
            }
            if (optionalUser.isEmpty()) {
                return new ApiResponse("User not found", false);
            }
            Product product = optionalProduct.get();
            BasketItem basketItem = BasketItem.builder()
                    .productName(product.getName())
                    .productId(product.getId())
                    .quantity(basketDto.getQuantity())
                    .price(product.getPrice() * basketDto.getQuantity())
                    .build();
            Basket basket = Basket.builder()
                    .basketItems(List.of(basketItem))
                    .totalPrice(basketItem.getPrice())
                    .user(optionalUser.get())
                    .build();
            basketRepository.save(basket);
            return new ApiResponse("Product successfully added to basket", true);
        }
        return updateBasket(optionalUserBasket, basketDto);
    }

    private ApiResponse updateBasket(Optional<Basket> optionalUserBasket, BasketDto basketDto) {
        Optional<Product> optionalProduct = productRepository.findById(basketDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
        if (optionalProduct.isEmpty()) {
            return new ApiResponse("Product not found", false);
        }
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        Product product = optionalProduct.get();
        BasketItem basketItem = BasketItem.builder()
                .productName(product.getName())
                .productId(product.getId())
                .quantity(basketDto.getQuantity())
                .price(product.getPrice() * basketDto.getQuantity())
                .build();
        Basket basket = optionalUserBasket.get();
        basket.getBasketItems().add(basketItem);
        basket.setTotalPrice(basket.getTotalPrice() + basketItem.getPrice());
        basketRepository.save(basket);
        return new ApiResponse("Product successfully added to basket", true);
    }

    public ApiResponse deleteProductFromBasket(Integer userId, Integer productId) {
        try {
            Optional<BasketItem> optionalBasketItem = basketItemRepository.findByProductId(productId);
            if (optionalBasketItem.isEmpty()) {
                return new ApiResponse("Some error arise", false);
            }
            Optional<Basket> optionalBasket = basketRepository.findByUserId(userId);
            if (optionalBasket.isEmpty()) {
                return new ApiResponse("Some error arise", false);
            }
            Basket basket = optionalBasket.get();
            basket.getBasketItems().remove(optionalBasketItem.get());
            basket.setTotalPrice(basket.getTotalPrice() - optionalBasketItem.get().getPrice());
            basketRepository.save(basket);
            return new ApiResponse("Product removed from basket", true);
        } catch (Exception e) {
            return new ApiResponse("Some error arise", false);
        }
    }

}
