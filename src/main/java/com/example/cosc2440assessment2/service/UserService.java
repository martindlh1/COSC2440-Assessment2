package com.example.cosc2440assessment2.service;

import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.PolicyOwner;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.repository.UserRepository;
import com.example.cosc2440assessment2.singleton.Auth;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = UserRepository.getInstance();

    public User getUserByUsername(String username) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{});
        return userRepository.getUserByUsername(username);
    }

    public void addUser(User user) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{Role.ADMIN});
        userRepository.addUser(user);
    }

    public PolicyOwner getPolicyOwner(User user) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{Role.POLICY_OWNER});
        return userRepository.getPolicyOwner(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
