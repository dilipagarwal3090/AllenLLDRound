package com.allen.repository;

import com.allen.model.Item;
import com.allen.repository.interfaces.InventoryRepository;

import java.util.HashMap;
import java.util.Map;

public class InventoryRepositoryImpl implements InventoryRepository {
    Map<Integer, Item> inventory = new HashMap<>();
    int nextid = 1;

    @Override
    public void save(Item item) {
        if (item != null && item.getId() == 0) {
            item.setId(nextid++);
        }
        inventory.put(item.getId(), item);
    }

    @Override
    public void delete(int itemId) {
        inventory.remove(itemId);
    }

    @Override
    public Item findById(int itemId) {
        if (inventory.containsKey(itemId)) {
            return inventory.get(itemId);
        }
        return null;
    }
}
