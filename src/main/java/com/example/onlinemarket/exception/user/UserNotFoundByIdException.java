package com.example.onlinemarket.exception.user;

import lombok.Getter;

@Getter
public class UserNotFoundByIdException extends RuntimeException{
    private final Integer id;

    public UserNotFoundByIdException(Integer id) {
        super("error.not_found.user.by_email");
        this.id = id;
    }
}
