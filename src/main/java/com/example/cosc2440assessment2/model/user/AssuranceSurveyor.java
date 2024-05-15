package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class AssuranceSurveyor extends Provider {
    public AssuranceSurveyor(String username, String password, String fullName) {
        super(username, password, fullName, Role.ASSURANCE_SURVEYOR);
    }
}
