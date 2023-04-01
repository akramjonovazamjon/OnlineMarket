package com.example.onlinemarket.exception.category;

import lombok.Getter;

@Getter
public class CategoryNotFoundByNameException extends RuntimeException{
    private final String name;

    public CategoryNotFoundByNameException(String name) {
        super("error.not_found.category.by_name");
        this.name = name;
    }
}
