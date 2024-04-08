package com.fallinnadim.orderservice.service;

import com.fallinnadim.orderservice.dto.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}
