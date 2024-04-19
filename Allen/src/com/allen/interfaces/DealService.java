package com.allen.interfaces;

import java.util.Date;

public interface DealService {
    void createDeal(double price, int quantity, Date endTime);

    void endDeal(int dealId);

    void updateDeal(int dealId, int quantity, Date endTime);
}
