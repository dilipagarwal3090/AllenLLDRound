package com.allen.repository.interfaces;

import com.allen.model.Item;

public interface InventoryRepository {
    void save(Item item);

    void delete(int itemId);

    Item findById(int itemId);
}
