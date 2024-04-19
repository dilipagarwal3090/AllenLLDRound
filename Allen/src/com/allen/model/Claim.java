package com.allen.model;

import com.allen.enums.ClaimStatus;
import lombok.Data;

@Data
public class Claim {
    int id;
    int delaId;
    String userName;
    ClaimStatus claimStatus;
}
