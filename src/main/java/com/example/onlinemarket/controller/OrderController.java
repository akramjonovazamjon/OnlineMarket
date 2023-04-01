package com.example.onlinemarket.controller;

import com.example.onlinemarket.controller.vm.OrderVm;
import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.dto.OrderDto;
import com.example.onlinemarket.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{id}")
    public ResponseData<OrderVm> getOrderById(@PathVariable Integer id) {
        OrderVm order = orderService.getOrderById(id);
        return ResponseData.of(order);
    }


    @GetMapping
    public ResponseData<List<OrderVm>> getOrdersByAccepted(@RequestParam boolean accepted) {
        List<OrderVm> ordersByAccepted = orderService.getOrdersByAccepted(accepted);
        return ResponseData.of(ordersByAccepted);
    }


    @PostMapping
    public ResponseData<OrderVm> addOrder(@RequestBody @Valid OrderDto dto) {
        OrderVm orderVm = orderService.addOrder(dto);
        return ResponseData.of(orderVm);
    }


    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }


    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
        orderService.updateOrder(id, orderDto);
    }

}
