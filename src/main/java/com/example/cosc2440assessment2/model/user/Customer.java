/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class Customer extends User {

    public Customer(String username, String password, String fullName, String email, String phone, String address, Role role) {
        super(username, password, fullName, email, phone, address, role);
    }

    public Customer(Integer id, String username, String password, String fullName, String email, String phone, String address, Role role) {
        super(id, username, password, fullName, email, phone, address, role);
    }

    public Customer(User user) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getRole());
    }
}
