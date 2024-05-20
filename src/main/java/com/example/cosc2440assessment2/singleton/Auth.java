/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.singleton;

import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.User;

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

    public static void isAuthorized(Role[] authorizedRoles) throws UnauthorizedException {
        if (authorizedRoles.length == 0)
            return;
        if (getInstance().user == null)
            throw new UnauthorizedException("Not connected");
        for (Role role : authorizedRoles) {
            if (getInstance().user.getRole().equals(role))
                return;
        }
        throw new UnauthorizedException("Role " + getInstance().user.getRole() + " not authorized");
    }
}
