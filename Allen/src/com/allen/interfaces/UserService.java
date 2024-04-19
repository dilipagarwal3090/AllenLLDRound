package com.allen.interfaces;

import com.allen.model.User;

public interface UserService {
    void registerUser(String userName);

    User getUser(String userName);
}
