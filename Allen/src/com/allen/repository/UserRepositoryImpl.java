package com.allen.repository;

import com.allen.model.User;
import com.allen.repository.interfaces.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserName(), user);
    }

    @Override
    public void delete(String userId) {
        users.remove(userId);
    }

    @Override
    public User findByUserName(String userName) {
        if (users.containsKey(userName)) {
            return users.get(userName);
        }
        return null;
    }
}
