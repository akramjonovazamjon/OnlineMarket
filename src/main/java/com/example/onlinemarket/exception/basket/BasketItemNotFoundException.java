package com.example.onlinemarket.exception.basket;

public class BasketItemNotFoundException extends RuntimeException{
    public BasketItemNotFoundException() {
        super("error.not_found.basket_item");
    }
}
