package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public abstract class Customer extends User {
    public Customer(String username, String password, String fullName, Role role) {
        super(username, password, fullName, role);
    }
}
