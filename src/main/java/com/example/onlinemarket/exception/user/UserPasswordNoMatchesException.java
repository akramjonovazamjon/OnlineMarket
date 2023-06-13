package com.example.onlinemarket.exception.user;

import lombok.Getter;

@Getter
public class UserPasswordNoMatchesException extends RuntimeException {
    private final String password;

    public UserPasswordNoMatchesException(String password) {
        super("error.no_matches.user_password");
        this.password = password;
    }
}
