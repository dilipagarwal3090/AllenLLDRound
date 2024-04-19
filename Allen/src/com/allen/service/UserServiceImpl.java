package com.allen.service;

import com.allen.enums.UserType;
import com.allen.interfaces.UserService;
import com.allen.model.User;
import com.allen.repository.interfaces.UserRepository;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String userName) {
        User user = User.builder().userType(UserType.REGULAR).userName(userName).build();
        userRepository.save(user);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }
}
