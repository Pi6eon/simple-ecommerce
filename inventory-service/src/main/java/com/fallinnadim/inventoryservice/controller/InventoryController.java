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
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String sku_code, @RequestParam Integer quantity) {
        return inventoryService.isInStock(sku_code, quantity);
    }
}
