package com.example.onlinemarket.controller.vm;

import java.sql.Timestamp;

public record ProductVm(Integer id, String name, String info, Double price, Integer quantity,
                        Timestamp createdAt, Timestamp updatedAt, Integer createdBy, Integer updatedBy, Integer categoryId) {
}
