package com.allen.repository.interfaces;

import com.allen.model.Claim;

public interface ClaimRepository {
    void save(Claim claim);

    void delete(Claim claim);

    Claim findByDealAndUser(int dealId, String userName);
}
