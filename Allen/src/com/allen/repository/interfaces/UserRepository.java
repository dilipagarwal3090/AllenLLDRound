package com.allen.repository.interfaces;

import com.allen.model.User;

public interface UserRepository {
    void save(User user);

    void delete(String userId);

    User findByUserName(String userName);
}
