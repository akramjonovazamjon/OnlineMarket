package com.example.onlinemarket.service;

import com.example.onlinemarket.controller.vm.OrderVm;
import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.entity.Order;
import com.example.onlinemarket.dto.OrderAcceptedDto;
import com.example.onlinemarket.dto.OrderDto;
import com.example.onlinemarket.exception.basket.BasketNotFoundException;
import com.example.onlinemarket.exception.order.OrderNotFoundException;
import com.example.onlinemarket.repository.BasketRepository;
import com.example.onlinemarket.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Basket> optionalBasket = basketRepository.findById(dto.basketId());
        if (optionalBasket.isEmpty()) {
            throw new BasketNotFoundException();
        }
        Order order = Order.of(optionalBasket.get());
        return orderRepository.save(order).from();
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    public void updateOrder(Integer id, OrderDto dto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException();
        }
        Optional<Basket> optionalBasket = basketRepository.findById(dto.basketId());
        if (optionalBasket.isEmpty()) {
            throw new BasketNotFoundException();
        }
        Order order = optionalOrder.get();
        order.setBasket(optionalBasket.get());
        order.setAccepted(dto.accepted());
        orderRepository.save(order);
    }
}
