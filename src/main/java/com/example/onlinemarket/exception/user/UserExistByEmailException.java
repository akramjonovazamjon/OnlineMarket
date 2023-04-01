package com.example.onlinemarket.exception.user;

import lombok.Getter;

@Getter
public class UserExistByEmailException extends RuntimeException {
    private final String email;

    public UserExistByEmailException(String email) {
        super("error.already.exist.by_email");
        this.email = email;
    }
}
