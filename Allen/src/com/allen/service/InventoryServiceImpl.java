package com.allen.service;

import com.allen.enums.ItemStatus;
import com.allen.exception.BusinessException;
import com.allen.interfaces.InventoryService;
import com.allen.model.Item;
import com.allen.repository.interfaces.InventoryRepository;

public class InventoryServiceImpl implements InventoryService {
    InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void addItemToInventory(Item item) {
        inventoryRepository.save(item);
    }

    @Override
    public boolean isItemAvailable(int itemId) {
        Item item = inventoryRepository.findById(itemId);
        return item != null && item.getStatus() == ItemStatus.AVAILABLE;
    }

    @Override
    public void markItemAsSold(int itemId) {
        Item item = inventoryRepository.findById(itemId);
        if (item != null) {
            item.setStatus(ItemStatus.SOLD_OUT);
            inventoryRepository.save(item);
        } else {
            throw new BusinessException("Item with id " + itemId + "not found in inventory");
        }
    }
}
