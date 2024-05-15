package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public abstract class Provider extends Customer {
    public Provider(String username, String password, String fullName, Role role) {
        super(username, password, fullName, role);
    }
}
