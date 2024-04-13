package com.fallinnadim.inventoryservice.service;

import com.fallinnadim.inventoryservice.dto.InventoryResponse;
import com.fallinnadim.inventoryservice.model.Inventory;
import com.fallinnadim.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    @Override
    public boolean isInStock(String sku_code, Integer quantity) {
        log.info("checking if product in stock");
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(sku_code, quantity);
    }
}
