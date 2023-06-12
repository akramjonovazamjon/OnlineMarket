package com.example.onlinemarket.service;

import com.example.onlinemarket.controller.vm.OrderVm;
import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.entity.Order;
import com.example.onlinemarket.dto.OrderDto;
import com.example.onlinemarket.exception.basket.BasketNotFoundException;
import com.example.onlinemarket.exception.order.OrderNotFoundException;
import com.example.onlinemarket.repository.BasketRepository;
import com.example.onlinemarket.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;


    public OrderVm getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new).from();
    }

    public List<OrderVm> getOrdersByAccepted(boolean accepted) {
        return orderRepository.findAllByAccepted(accepted).stream().map(Order::from).toList();
    }

    public OrderVm addOrder(OrderDto dto) {
        Basket basket = basketRepository.findById(dto.basketId()).orElseThrow(BasketNotFoundException::new);
        Order order = Order.of(basket);
        return orderRepository.save(order).from();
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    public void updateOrder(Integer id, OrderDto dto) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        Basket basket = basketRepository.findById(dto.basketId()).orElseThrow(BasketNotFoundException::new);

        order.setBasket(basket);
        order.setAccepted(dto.accepted());
        orderRepository.save(order);
    }
}
