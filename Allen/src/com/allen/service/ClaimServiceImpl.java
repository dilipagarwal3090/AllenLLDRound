package com.allen.service;

import com.allen.ConcurrencyManager;
import com.allen.enums.ClaimStatus;
import com.allen.exception.BusinessException;
import com.allen.model.Claim;
import com.allen.repository.interfaces.ClaimRepository;
import com.allen.interfaces.ClaimService;

public class ClaimServiceImpl implements ClaimService {

    ClaimRepository claimRepository;
    ConcurrencyManager concurrencyManager;

    public ClaimServiceImpl(ClaimRepository claimRepository, ConcurrencyManager concurrencyManager) {
        this.claimRepository = claimRepository;
        this.concurrencyManager = concurrencyManager;
    }

    @Override
    public void claimDeal(int dealId, String userName) {
        try {
            concurrencyManager.acquire();
            if (canClaimDeal(dealId, userName)) {
                Claim claim = new Claim();
                claim.setUserName(userName);
                claim.setDelaId(dealId);
                claim.setClaimStatus(ClaimStatus.CLAIMED);
                claimRepository.save(claim);
            } else {
                throw new BusinessException("Deal can not be claimed by user " + userName);
            }
        } finally {
            concurrencyManager.release();
        }
    }

    @Override
    public boolean canClaimDeal(int dealId, String userName) {
        Claim existingClaim = claimRepository.findByDealAndUser(dealId, userName);
        if (existingClaim != null) {
            throw new BusinessException("user already claimed the deal");
        }
        return true;
    }
}
