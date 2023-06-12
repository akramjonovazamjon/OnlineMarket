package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.entity.BasketItem;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.dto.BasketDto;
import com.example.onlinemarket.exception.basket.BasketItemNotFoundException;
import com.example.onlinemarket.exception.basket.BasketNotFoundException;
import com.example.onlinemarket.exception.product.ProductNotFoundByIdException;
import com.example.onlinemarket.exception.user.UserNotFoundByEmailException;
import com.example.onlinemarket.repository.BasketItemRepository;
import com.example.onlinemarket.repository.BasketRepository;
import com.example.onlinemarket.repository.ProductRepository;
import com.example.onlinemarket.repository.UserRepository;
import com.example.onlinemarket.util.SecurityUtil;
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
        return basketRepository.findByUserId(userId).orElseThrow(BasketNotFoundException::new);
    }

    public Basket addToBasket(BasketDto dto) {
        Optional<Basket> optionalUserBasket = basketRepository.findByUserId(dto.userId());
        if (optionalUserBasket.isEmpty()) {

            String username = SecurityUtil.username();

            User user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundByEmailException(username));

            Product product = productRepository.findById(dto.productId()).orElseThrow(() -> new ProductNotFoundByIdException(dto.productId()));

            BasketItem basketItem = BasketItem.of(product.getId(), dto.quantity(), product.getPrice() * dto.quantity());

            Basket basket = Basket.of(List.of(basketItem), basketItem.getPrice(), user);

            return basketRepository.save(basket);
        }
        return updateBasket(optionalUserBasket.get(), dto);
    }

    private Basket updateBasket(Basket basket, BasketDto dto) {
        Product product = productRepository.findById(dto.productId()).orElseThrow(() -> new ProductNotFoundByIdException(dto.productId()));
        BasketItem basketItem = BasketItem.of(product.getId(), dto.quantity(), product.getPrice() * dto.quantity());
        basket.getBasketItems().add(basketItem);
        basket.setTotalPrice(basket.getTotalPrice() + basketItem.getPrice());
        return basketRepository.save(basket);
    }

    public void deleteProductFromBasket(Integer productId) {
        BasketItem basketItem = basketItemRepository.findByProductId(productId).orElseThrow(BasketItemNotFoundException::new);

        String username = SecurityUtil.username();

        Optional<Basket> optionalBasket = basketRepository.findByUserEmail(username);

        Basket basket = optionalBasket.get();
        basket.getBasketItems().remove(basketItem);
        basket.setTotalPrice(basket.getTotalPrice() - basketItem.getPrice());
        basketRepository.save(basket);
    }

}
