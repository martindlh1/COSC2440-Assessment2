package com.example.cosc2440assessment2.sql;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSQLCommand {
    public static String getUserByUsername(String username) {
        return "SELECT * FROM user_ WHERE (username = '" + username + "');";
    }

    public static String updateUser(User user) {
        return "update user_ set username = '" + user.getUsername() + "', phone = '" + user.getPhone() + "', email = '" + user.getEmail() + "', address = '" + user.getAddress() + "', fullName = '" + user.getFullName() + "' where username = '" + user.getUsername() + "';";
    }

    public static String getAllUsers() {
        return "SELECT * FROM user_";
    }

    public static String addUser(User user) {
        return "INSERT INTO user_ (username,password,fullName,role) "
                + "VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFullName() + "', '" + user.getRole() + "');";
    }

    public static User getUserFromRes(ResultSet res) throws SQLException {
        if (!res.next())
            return null;
        return new User(res.getString("username"), res.getString("password"), res.getString("fullName"), "", "", "", Role.valueOf(res.getString("role")));
    }

    public static List<User> getUsersFromRes(ResultSet res) throws SQLException {
        List<User> users = new ArrayList<>();
        while (res.next()) {
            User user = new User(res.getString("username"), res.getString("password"), res.getString("fullName"), "", "", "", Role.valueOf(res.getString("role")));
            users.add(user);
        }
        return users;
    }
}
