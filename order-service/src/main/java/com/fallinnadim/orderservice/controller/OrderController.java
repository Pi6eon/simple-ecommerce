package com.fallinnadim.orderservice.controller;

import com.fallinnadim.orderservice.dto.OrderRequest;
import com.fallinnadim.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }
}
