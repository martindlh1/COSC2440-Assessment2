package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.PolicyOwner;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.singleton.Database;
import com.example.cosc2440assessment2.sql.UserSQLCommand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final Database database = Database.getInstance();

    public void addUser(User user) {
    }

    public void deleteUser(User user) {

    }

    public void updateUser(User user) {
        try {
            Statement statement = database.getDb().createStatement();
            statement.executeUpdate(UserSQLCommand.updateUser(user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(UserSQLCommand.getUserByUsername(username));
            res.next();
            return new User(res.getInt("uID"), res.getString("username"), res.getString("password"), res.getString("fullName"),  res.getString("email"),  res.getString("phone"),  res.getString("address"), Role.valueOf(res.getString("role")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PolicyOwner getPolicyOwner(User user) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserRepository();
        return INSTANCE;
    }
}
