package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.PolicyOwner;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.singleton.Database;
import com.example.cosc2440assessment2.sql.UserSQLCommand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository INSTANCE;
    private final Database database = Database.getInstance();

    public void addUser(User user) {
    }

    public void deleteUser(User user) {

    }

    public List<User> getDependentsByHolder(User holder) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(UserSQLCommand.getDependentsByHolder(holder));
            List<User> dependents = new ArrayList<>();
            while (res.next()) {
                dependents.add(new User(res.getString("username"),
                        res.getString("password"),
                        res.getString("fullname"),
                        res.getString("email"),
                        res.getString("phone"),
                        res.getString("address"),
                        Role.valueOf(res.getString("role"))));
            }
            return dependents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getBeneficiariesByOwner(User owner) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(UserSQLCommand.getBeneficiariesByOwner(owner));
            List<User> beneficiaries = new ArrayList<>();
            while (res.next()) {
                beneficiaries.add(new User(res.getString("username"),
                        res.getString("password"),
                        res.getString("fullname"),
                        res.getString("email"),
                        res.getString("phone"),
                        res.getString("address"),
                        Role.valueOf(res.getString("role"))));
            }
            return beneficiaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            return new User(res.getString("username"), res.getString("password"), res.getString("fullName"), "", "", "", Role.valueOf(res.getString("role")));
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
