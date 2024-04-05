package com.fallinnadim.orderservice.service;

import com.fallinnadim.orderservice.dto.InventoryResponse;
import com.fallinnadim.orderservice.dto.OrderLineItemsDto;
import com.fallinnadim.orderservice.dto.OrderRequest;
import com.fallinnadim.orderservice.model.Order;
import com.fallinnadim.orderservice.model.OrderLineItems;
import com.fallinnadim.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder; // Bean
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        // Call Inventory Service and place order if product is in stock
        InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()
                .uri("http://localhost:8082/api/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        log.info("Inventory array response : {}", inventoryResponsesArray);

        if (skuCodes.size() != inventoryResponsesArray.length) {
            throw new IllegalArgumentException("wronk skucodes, please try again");
        }
        boolean allProductInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock); // check if all element is true
        log.info("Check element : {}", allProductInStock);
        if (!allProductInStock) {
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }
        orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
