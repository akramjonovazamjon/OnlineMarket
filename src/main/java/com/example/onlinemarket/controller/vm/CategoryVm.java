package com.example.onlinemarket.controller.vm;

import java.sql.Timestamp;

public record CategoryVm(Integer id, String name, Timestamp createdAt, Timestamp updatedAt, Integer createdBy, Integer updatedBy) {
}
