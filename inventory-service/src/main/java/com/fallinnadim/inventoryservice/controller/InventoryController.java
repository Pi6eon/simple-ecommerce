package com.fallinnadim.inventoryservice.controller;

import com.fallinnadim.inventoryservice.dto.InventoryResponse;
import com.fallinnadim.inventoryservice.service.InventoryService;
import com.fallinnadim.inventoryservice.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;
    // http://localhost:8082/api/inventories?skuCode=Iphone14Pro&skuCode=Iphone15Pro
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
}
