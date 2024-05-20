package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class PolicyOwner extends Customer {
    private Customer[] beneficiaries;


    public PolicyOwner(String username, String password, String fullName, String email, String phone, String address) {
        super(username, password, fullName, email, phone, address, Role.POLICY_OWNER);
    }

    public PolicyOwner(User user) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress(), Role.POLICY_OWNER);
    }
}
