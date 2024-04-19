package com.allen.model;

import com.allen.enums.DealStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Deal {
    int id;
    double price;
    int quantity;
    Date endTime;
    DealStatus status;
}
