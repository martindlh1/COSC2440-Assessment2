package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.helper.Database;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final Database database = Database.getInstance();

    public void addUser(User user) {
        try {
            Statement statement = database.getDb().createStatement();
            String sql = "INSERT INTO myuser (username,password,fullName,role) "
                    + "VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFullName() + "', '" + user.getRole() + "');";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username) {
        try {
            Statement statement = database.getDb().createStatement();
            String sql = "SELECT * FROM myuser WHERE (username = '" + username + "');";
            ResultSet res = statement.executeQuery(sql);
            if (!res.next())
                return null;
            User user = new User(res.getString("username"), res.getString("password"), res.getString("fullName"), Role.valueOf(res.getString("role")));
            res.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            Statement statement = database.getDb().createStatement();
            String sql = "SELECT * FROM myuser";
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                User user = new User(res.getString("username"), res.getString("password"), res.getString("fullName"), Role.valueOf(res.getString("role")));
                users.add(user);
            }
            res.close();
            statement.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserRepository();
        return INSTANCE;
    }
}
