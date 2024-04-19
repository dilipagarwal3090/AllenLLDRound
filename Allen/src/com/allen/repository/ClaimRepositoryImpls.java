package com.allen.repository;

import com.allen.model.Claim;
import com.allen.repository.interfaces.ClaimRepository;

import java.util.HashMap;
import java.util.Map;

public class ClaimRepositoryImpls implements ClaimRepository {
    Map<Integer, Claim> claims = new HashMap<Integer, Claim>();
    int nextId = 1;

    @Override
    public void save(Claim claim) {
        if (claim.getId() == 0) {
            claim.setId(nextId++);
        }
        claims.put(claim.getId(), claim);
    }

    @Override
    public void delete(Claim claim) {
        claims.remove(claim.getId());
    }

    @Override
    public Claim findByDealAndUser(int dealId, String userName) {
        for (Claim claim : claims.values()) {
            if (claim.getDelaId() == dealId && claim.getUserName().equals(userName)) {
                return claim;
            }
        }
        return null;
    }
}
