package com.example.onlinemarket.exception.product;

import lombok.Getter;

@Getter
public class ProductNotFoundByIdException extends RuntimeException {
    private final Integer id;

    public ProductNotFoundByIdException(Integer id) {
        super("error.not_found.product.by_id");
        this.id = id;
    }
}
