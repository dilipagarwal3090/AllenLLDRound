package com.allen.repository.interfaces;

import com.allen.model.Deal;

public interface DealRepository {
    void save(Deal deal);

    void delete(Deal deal);

    Deal findById(int dealId);
}
