/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class AssuranceManager extends Provider {
    private AssuranceSurveyor[] surveyors;


    public AssuranceManager(String username, String password, String fullName, String email, String phone, String address) {
        super(username, password, fullName, email, phone, address, Role.ASSURANCE_MANAGER);
    }
}
