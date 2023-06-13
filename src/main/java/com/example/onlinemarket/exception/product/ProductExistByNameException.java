package com.example.onlinemarket.exception.product;

import lombok.Getter;

@Getter
public class ProductExistByNameException extends RuntimeException {
    private final String name;

    public ProductExistByNameException(String name) {
        super("error.exist.product.by_name");
        this.name = name;
    }
}
