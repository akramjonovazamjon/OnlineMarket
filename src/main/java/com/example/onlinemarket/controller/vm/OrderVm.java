package com.example.onlinemarket.controller.vm;

import com.example.onlinemarket.entity.Basket;

public record OrderVm(Integer id, Basket basket, boolean accepted) {
}
