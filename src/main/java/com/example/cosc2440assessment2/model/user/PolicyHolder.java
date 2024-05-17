package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class PolicyHolder extends Customer {
    private Dependent[] dependents;

    public PolicyHolder(String username, String password, String fullName, String email, String phone, String address) {
        super(username, password, fullName, email, phone, address, Role.POLICY_HOLDER);
    }
}
