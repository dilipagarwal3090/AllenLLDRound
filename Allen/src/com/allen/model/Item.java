package com.allen.model;

import com.allen.enums.ItemStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class Item {
    int id;
    String name;
    double price;
    int quantity;
    ItemStatus status;
}
