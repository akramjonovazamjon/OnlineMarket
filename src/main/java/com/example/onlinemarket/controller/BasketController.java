package com.example.onlinemarket.controller;

import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.dto.BasketDto;
import com.example.onlinemarket.service.BasketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/{userId}")
    public ResponseData<Basket> getUserBasket(@PathVariable Integer userId) {
        Basket userBasket = basketService.getUserBasket(userId);
        return ResponseData.of(userBasket);
    }


    @PostMapping
    public ResponseData<Basket> addToBasket(@RequestBody BasketDto dto) {
        Basket basket = basketService.addToBasket(dto);
        return ResponseData.of(basket);
    }


    @DeleteMapping("/{userId}/{productId}")
    public void deleteProductFromBasket(@PathVariable Integer productId, @PathVariable Integer userId) {
        basketService.deleteProductFromBasket(userId, productId);
    }
}
