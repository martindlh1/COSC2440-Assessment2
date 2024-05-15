package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class AssuranceManager extends Provider {
    private AssuranceSurveyor[] surveyors;

    public AssuranceManager(String username, String password, String fullName) {
        super(username, password, fullName, Role.ASSURANCE_MANAGER);
    }
}
