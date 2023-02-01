package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.Order;
import com.example.onlinemarket.payload.OrderAcceptedDto;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.OrderDto;
import com.example.onlinemarket.service.OrderService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * GET ORDER BY ID
     *
     * @param id INTEGER
     * @return ORDER
     */
    @GetMapping("/{id}")
    public HttpEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    /**
     * GET ORDERS BY ACCEPTED
     *
     * @param orderAcceptedDto OrderAcceptedDto
     * @return LIST
     */
    @GetMapping
    public HttpEntity<List<Order>> getOrdersByAccepted(@RequestBody OrderAcceptedDto orderAcceptedDto) {
        List<Order> ordersByAccepted = orderService.getOrdersByAccepted(orderAcceptedDto);
        return ResponseEntity.ok(ordersByAccepted);
    }

    /**
     * ADD ORDER
     *
     * @param orderDto OrderDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addOrder(@RequestBody OrderDto orderDto) {
        ApiResponse apiResponse = orderService.addOrder(orderDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE ORDER BY ID
     *
     * @param id INTEGER
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteOrderById(@PathVariable Integer id) {
        ApiResponse apiResponse = orderService.deleteOrderById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * UPDATE ORDER
     *
     * @param id       INTEGER
     * @param orderDto OrderDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateOrder(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
        ApiResponse apiResponse = orderService.updateOrder(id, orderDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
