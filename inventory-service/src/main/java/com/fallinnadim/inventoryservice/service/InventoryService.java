package com.fallinnadim.inventoryservice.service;

public interface InventoryService {
    boolean isInStock(String sku_code, Integer quantity);

    }
