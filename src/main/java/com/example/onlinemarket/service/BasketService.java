package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.entity.BasketItem;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.dto.BasketDto;
import com.example.onlinemarket.exception.basket.BasketItemNotFoundException;
import com.example.onlinemarket.exception.product.ProductNotFoundByIdException;
import com.example.onlinemarket.exception.user.UserNotFoundByIdException;
import com.example.onlinemarket.repository.BasketItemRepository;
import com.example.onlinemarket.repository.BasketRepository;
import com.example.onlinemarket.repository.ProductRepository;
import com.example.onlinemarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BasketItemRepository basketItemRepository;


    public Basket getUserBasket(Integer userId) {
        return basketRepository.findByUserId(userId).orElse(null);
    }

    public Basket addToBasket(BasketDto dto) {
        Optional<Basket> optionalUserBasket = basketRepository.findByUserId(dto.userId());
        if (optionalUserBasket.isEmpty()) {
            Optional<Product> optionalProduct = productRepository.findById(dto.productId());
            Optional<User> optionalUser = userRepository.findById(dto.userId());
            if (optionalProduct.isEmpty()) {
                throw new ProductNotFoundByIdException(dto.productId());
            }
            if (optionalUser.isEmpty()) {
                throw new UserNotFoundByIdException(dto.userId());
            }
            Product product = optionalProduct.get();
            BasketItem basketItem = BasketItem.builder()
                    .productId(product.getId())
                    .quantity(dto.quantity())
                    .price(product.getPrice() * dto.quantity())
                    .build();
            Basket basket = Basket.builder()
                    .basketItems(List.of(basketItem))
                    .totalPrice(basketItem.getPrice())
                    .user(optionalUser.get())
                    .build();
            return basketRepository.save(basket);
        }
        return updateBasket(optionalUserBasket.get(), dto);
    }

    private Basket updateBasket(Basket basket, BasketDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(dto.productId());
        Optional<User> optionalUser = userRepository.findById(dto.userId());
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundByIdException(dto.productId());
        }
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundByIdException(dto.userId());
        }
        Product product = optionalProduct.get();
        BasketItem basketItem = BasketItem.builder()
                .productId(product.getId())
                .quantity(dto.quantity())
                .price(product.getPrice() * dto.quantity())
                .build();
        basket.getBasketItems().add(basketItem);
        basket.setTotalPrice(basket.getTotalPrice() + basketItem.getPrice());
        return basketRepository.save(basket);
    }

    public void deleteProductFromBasket(Integer userId, Integer productId) {
        Optional<BasketItem> optionalBasketItem = basketItemRepository.findByProductId(productId);
        if (optionalBasketItem.isEmpty()) {
            throw new BasketItemNotFoundException();
        }
        Optional<Basket> optionalBasket = basketRepository.findByUserId(userId);

        Basket basket = optionalBasket.get();
        basket.getBasketItems().remove(optionalBasketItem.get());
        basket.setTotalPrice(basket.getTotalPrice() - optionalBasketItem.get().getPrice());
        basketRepository.save(basket);
    }

}
