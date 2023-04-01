package com.example.onlinemarket.exception.category;

import lombok.Getter;

@Getter
public class CategoryExistByNameException extends RuntimeException{
    private final String name;

    public CategoryExistByNameException(String name) {
        super("error.exist.category.by_name");
        this.name = name;
    }
}
