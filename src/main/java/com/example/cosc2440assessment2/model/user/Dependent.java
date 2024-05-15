package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class Dependent extends Customer {
    public Dependent(String username, String password, String fullName) {
        super(username, password, fullName, Role.DEPENDENT);
    }
}
