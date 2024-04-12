package com.fallinnadim.inventoryservice.service;

import com.fallinnadim.inventoryservice.dto.InventoryResponse;
import com.fallinnadim.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryService {
    boolean isInStock(String sku_code, Integer quantity);

    }
