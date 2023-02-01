package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.BasketDto;
import com.example.onlinemarket.service.BasketService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    /**
     * GET USER BASKET IF BASKET IS NULL THEN USER BASKET IS EMPTY
     *
     * @param userId INTEGER
     * @return BASKET
     */
    @GetMapping("/{userId}")
    public HttpEntity<Basket> getUserBasket(@PathVariable Integer userId) {
        Basket userBasket = basketService.getUserBasket(userId);
        return ResponseEntity.ok(userBasket);
    }

    /**
     * ADD PRODUCT TO BASKET
     *
     * @param basketDto BasketDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addToBasket(@RequestBody BasketDto basketDto) {
        ApiResponse apiResponse = basketService.addToBasket(basketDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE PRODUCT FROM BASKET
     *
     * @param productId INTEGER
     * @param userId    INTEGER
     * @return ApiResponse
     */
    @DeleteMapping("/{userId}/{productId}")
    public HttpEntity<ApiResponse> deleteProductFromBasket(@PathVariable Integer productId, @PathVariable Integer userId) {
        ApiResponse apiResponse = basketService.deleteProductFromBasket(userId, productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
