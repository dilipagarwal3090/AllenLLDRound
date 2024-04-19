package com.allen.service;

import com.allen.enums.DealStatus;
import com.allen.exception.BusinessException;
import com.allen.interfaces.DealService;
import com.allen.model.Deal;
import com.allen.repository.interfaces.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    DealRepository dealRepository;

    @Override
    public void createDeal(double price, int quantity, Date endTime) {
        Deal deal = Deal.builder().price(price).quantity(quantity).endTime(endTime).status(DealStatus.ACTIVE).build();
        dealRepository.save(deal);
    }

    @Override
    public void endDeal(int dealId) {
        Deal deal = dealRepository.findById(dealId);
        if (deal != null) {
            deal.setStatus(DealStatus.ENDED);
        } else {
            throw new BusinessException("Deal with Id " + dealId + "not found");
        }
    }

    @Override
    public void updateDeal(int dealId, int quantity, Date endTime) {
        Deal deal = dealRepository.findById(dealId);
        if (deal != null) {
            deal.setQuantity(quantity);
            deal.setEndTime(endTime);
            dealRepository.save(deal);
        } else {
            throw new BusinessException("Deal with Id " + dealId + "not found");
        }
    }
}
