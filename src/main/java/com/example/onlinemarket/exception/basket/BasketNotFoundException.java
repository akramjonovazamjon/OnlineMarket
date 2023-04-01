package com.example.onlinemarket.exception.basket;

public class BasketNotFoundException extends RuntimeException{
    public BasketNotFoundException() {
        super("error.not_found.basket");
    }
}
