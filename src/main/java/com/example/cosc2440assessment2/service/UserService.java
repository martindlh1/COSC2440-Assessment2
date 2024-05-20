/**
 * @author <Team 8>
 */
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
    private final Auth auth = Auth.getInstance();

    public User getUserByUsername(String username) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{});
        return userRepository.getUserByUsername(username);
    }

    public List<User> getAllCustomers() {
        return userRepository.getAllCustomers();
    }

    public void updateUser(User user) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{});
        userRepository.updateUser(user);
    }

    public void addUser(User user) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{Role.ADMIN, Role.POLICY_OWNER});
        if (auth.getUser().getRole() == Role.POLICY_OWNER)
            userRepository.addBeneficiaryToOwner(user, auth.getUser().getId());
        else
            userRepository.addUser(user);
    }

    public void deleteUser(User user) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{Role.ADMIN, Role.POLICY_OWNER});
        userRepository.deleteUser(user);
    }

    public PolicyOwner getPolicyOwner(User user) throws UnauthorizedException {
        Auth.isAuthorized(new Role[]{Role.POLICY_OWNER});
        return userRepository.getPolicyOwner(user);
    }

    public List<User> getDependentsByHolder(User holder) {
        return userRepository.getDependentsByHolder(holder);
    }

    public List<User> getBeneficiariesByOwner(User owner) {
        return userRepository.getBeneficiariesByOwner(owner);
    }

    public List<User> getSurveyorsByManager(User manager) {
        return userRepository.getSurveyorsByManager(manager);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
