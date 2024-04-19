package com.allen.interfaces;

public interface ClaimService {
    void claimDeal(int dealId, String userName);

    boolean canClaimDeal(int dealId, String userName);
}
