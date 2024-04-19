package com.allen.interfaces;

import com.allen.model.Item;

public interface InventoryService {
    void addItemToInventory(Item item);

    boolean isItemAvailable(int itemId);

    void markItemAsSold(int itemId);
}
