package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class AssuranceSurveyor extends Provider {

    public AssuranceSurveyor(String username, String password, String fullName, String email, String phone, String address) {
        super(username, password, fullName, email, phone, address, Role.ASSURANCE_SURVEYOR);
    }
}
