package com.example.onlinemarket.exception.user;

import lombok.Getter;

@Getter
public class UserNotFoundByEmailException extends RuntimeException {
    private final String email;

    public UserNotFoundByEmailException(String email) {
        super("error.not_found.user.by_email");
        this.email = email;
    }
}
