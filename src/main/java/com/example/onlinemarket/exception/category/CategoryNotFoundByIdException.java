package com.example.onlinemarket.exception.category;

import lombok.Getter;

@Getter
public class CategoryNotFoundByIdException extends RuntimeException {
    private final Integer id;

    public CategoryNotFoundByIdException(Integer id) {
        super("error.not_found.category.by_id");
        this.id = id;
    }
}
