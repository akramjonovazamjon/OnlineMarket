package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Basket;
import com.example.onlinemarket.entity.Order;
import com.example.onlinemarket.payload.OrderAcceptedDto;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.OrderDto;
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


    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByAccepted(OrderAcceptedDto orderAcceptedDto) {
        return orderRepository.findAllByAccepted(orderAcceptedDto.isAccepted());
    }

    public ApiResponse addOrder(OrderDto orderDto) {
        Optional<Basket> optionalBasket = basketRepository.findById(orderDto.getBasketId());
        if (optionalBasket.isEmpty()) {
            return new ApiResponse("Order not added", false);
        }
        Order order = Order.builder()
                .basket(optionalBasket.get())
                .accepted(orderDto.isAccepted())
                .build();
        orderRepository.save(order);
        return new ApiResponse("Order added", true);
    }

    public ApiResponse deleteOrderById(Integer id) {
        try {
            orderRepository.deleteById(id);
            return new ApiResponse("Order deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Some error arise", false);
        }
    }

    public ApiResponse updateOrder(Integer id, OrderDto orderDto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return new ApiResponse("Order not found", false);
        }
        Optional<Basket> optionalBasket = basketRepository.findById(orderDto.getBasketId());
        if (optionalBasket.isEmpty()) {
            return new ApiResponse("Basket not found", false);
        }
        Order order = optionalOrder.get();
        order.setBasket(optionalBasket.get());
        order.setAccepted(orderDto.isAccepted());
        orderRepository.save(order);
        return new ApiResponse("Order updated", true);
    }
}
