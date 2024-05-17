package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public abstract class Customer extends User {

    public Customer(String username, String password, String fullName, String email, String phone, String address, Role role) {
        super(username, password, fullName, email, phone, address, role);
    }
}
