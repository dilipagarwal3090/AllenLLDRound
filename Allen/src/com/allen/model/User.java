package com.allen.model;

import com.allen.enums.UserType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    String userName;
    UserType userType;
}
