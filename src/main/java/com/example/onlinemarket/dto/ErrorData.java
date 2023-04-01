package com.example.onlinemarket.dto;

public record ErrorData(int code, String message) {
    public static ErrorData of(int code, String message) {
        return new ErrorData(code, message);
    }
}
