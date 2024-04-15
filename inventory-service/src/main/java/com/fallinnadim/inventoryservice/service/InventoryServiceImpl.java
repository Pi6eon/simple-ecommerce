package com.fallinnadim.inventoryservice.service;

import com.fallinnadim.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
