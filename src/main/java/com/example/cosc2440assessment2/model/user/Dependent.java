package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class Dependent extends Customer {

    public Dependent(String username, String password, String fullName, String email, String phone, String address) {
        super(username, password, fullName, email, phone, address, Role.DEPENDENT);
    }
}
