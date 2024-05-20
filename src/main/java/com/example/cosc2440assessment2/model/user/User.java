/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.model.user;

import com.example.cosc2440assessment2.model.Role;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Role role;

    public User(Integer id, String username, String password, String fullName, String email, String phone, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public User(String username, String password, String fullName, String email, String phone, String address, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return fullName + " - " + role.name();
    }
}
