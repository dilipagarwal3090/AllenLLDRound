package com.allen.repository;

import com.allen.model.Deal;
import com.allen.repository.interfaces.DealRepository;

import java.util.HashMap;
import java.util.Map;

public class DealRepositoryImpl implements DealRepository {
    Map<Integer, Deal> deals = new HashMap<>();
    int nextId = 1;

    @Override
    public void save(Deal deal) {
        if (deal.getId() == 0) {
            deal.setId(nextId++);
        }
        deals.put(deal.getId(), deal);
    }

    @Override
    public void delete(Deal deal) {
        deals.remove(deal.getId());
    }

    @Override
    public Deal findById(int dealId) {
        if (deals.containsKey(dealId)) {
            return deals.get(dealId);
        }
        return null;
    }
}
