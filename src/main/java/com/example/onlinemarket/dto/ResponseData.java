package com.example.onlinemarket.dto;

public record ResponseData<T>(T result, ErrorData error) {

    public static <T> ResponseData<T> of(T result) {
        return new ResponseData<>(result, null);
    }

    public static ResponseData<Object> errorOf(int code, String message) {
        return new ResponseData<>(null, ErrorData.of(code, message));
    }

}
