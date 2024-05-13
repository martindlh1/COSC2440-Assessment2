package com.example.cosc2440assessment2.helper;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.User;

public class Auth {
    private static Auth INSTANCE;
    private User user;

    public Auth() {
        this.user = null;
    }

    public void login(User user) {
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }

    public User getUser() {
        return this.user;
    }

    public static Auth getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Auth();
        return INSTANCE;
    }

    public static boolean isAuthorized(Role[] authorizedRoles) {
        if (authorizedRoles.length == 0)
            return true;
        if (getInstance().user == null)
            return false;
        for (Role role : authorizedRoles) {
            if (getInstance().user.getRole().equals(role))
                return true;
        }
        return false;
    }
}
