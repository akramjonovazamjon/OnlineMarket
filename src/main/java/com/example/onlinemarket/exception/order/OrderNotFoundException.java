package com.example.onlinemarket.exception.order;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("error.not_found.order");
    }
}
