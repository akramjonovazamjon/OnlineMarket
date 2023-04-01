package com.example.onlinemarket.dto;


public record ProductEditDto(
        String name,
        String info,
        Double price,
        Integer quantity) {
}
