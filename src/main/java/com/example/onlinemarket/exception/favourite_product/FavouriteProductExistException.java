package com.example.onlinemarket.exception.favourite_product;


public class FavouriteProductExistException extends RuntimeException {
    public FavouriteProductExistException() {
        super("error.exist.favourite_product");
    }
}
