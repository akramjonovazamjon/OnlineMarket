package com.example.onlinemarket.exception.product;

import lombok.Getter;

@Getter
public class ProductNotFoundByNameException extends RuntimeException{
    private final String name;

    public ProductNotFoundByNameException(String name) {
        super("error.not_found.product.by_name");
        this.name = name;
    }
}
