package com.fallinnadim.orderservice.service;

import com.fallinnadim.orderservice.client.InventoryClient;
import com.fallinnadim.orderservice.dto.OrderRequest;
import com.fallinnadim.orderservice.model.Order;
import com.fallinnadim.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        // Call the inventory service
        Boolean isProductInStock = inventoryClient.isInStock(orderRequest.sku_code(), orderRequest.quantity());

        if (!isProductInStock) {
            throw new RuntimeException("product with skuCode " + orderRequest.sku_code() + " is not in stock");
        }

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(orderRequest.price())
                .skuCode(orderRequest.sku_code())
                .quantity(orderRequest.quantity())
                .build();

        orderRepository.save(order);
        return("Order Placed Successfully");
    }
}
