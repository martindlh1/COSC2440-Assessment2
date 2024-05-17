package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class Admin extends User {

    public Admin(String username, String password, String fullName, String email, String phone, String address) {
        super(username, password, fullName, email, phone, address, Role.ADMIN);
    }
}
