package com.example.cosc2440assessment2.service;

import com.example.cosc2440assessment2.helper.Auth;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.User;
import com.example.cosc2440assessment2.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = UserRepository.getInstance();

    public UserService() {
    }

    public User getUserByUsername(String username) {
        if (!Auth.isAuthorized(new Role[]{}))
            return null;
        return userRepository.getUserByUsername(username);
    }

    public void addUser(User user) {
        if (!Auth.isAuthorized(new Role[]{Role.ADMIN}))
            return;
        userRepository.addUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
