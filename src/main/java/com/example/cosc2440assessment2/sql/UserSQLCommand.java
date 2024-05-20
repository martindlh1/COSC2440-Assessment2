/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.sql;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSQLCommand {
    public static String addUser(User user) {
        return "INSERT INTO user_ (username,password,role,phone,address,email,fullname) VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getRole().name() + "','" + user.getPhone() + "','" + user.getAddress() + "','" + user.getEmail() + "','" + user.getFullName() + "');";
    }
    public static String addUserWithReturn(User user) {
        return "INSERT INTO user_ (username,password,role,phone,address,email,fullname) VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getRole().name() + "','" + user.getPhone() + "','" + user.getAddress() + "','" + user.getEmail() + "','" + user.getFullName() + "') returning \"uID\";";
    }

    public static String addBeneficiaryToOwner(int userId, int ownerId) {
        return "INSERT INTO policyowner_beneficiary (\"cpoID\",\"cbID\") VALUES (" + ownerId + "," + userId + ");";
    }

    public static String getUserByUsername(String username) {
        return "SELECT * FROM user_ WHERE (username = '" + username + "');";
    }

    public static String getAllCustomers() {
        return "select * from user_ where role = 'DEPENDENT' or role = 'POLICY_OWNER' or role = 'POLICY_HOLDER';";
    }

    public static String getAllUsers() {
        return "SELECT * FROM user_";
    }

    public static String updateUser(User user) {
        String sql = "update user_ set username = '" + user.getUsername() + "', phone = '" + user.getPhone() + "', email = '" + user.getEmail() + "', address = '" + user.getAddress() + "', fullname = '" + user.getFullName() + "' where username = '" + user.getUsername() + "';";
        System.out.println(sql);
        return sql;
    }

    public static String getDependentsByHolder(User user) {
        return "select policyholder_dependent.*, user_.* from policyholder_dependent, user_ where \"cphID\" = " + user.getId() + " and user_.\"uID\" = policyholder_dependent.\"cdID\";";
    }

    public static String getBeneficiariesByOwner(User user) {
        return "select policyowner_beneficiary.*, user_.* " +
                "from policyowner_beneficiary, user_ " +
                "where policyowner_beneficiary.\"cpoID\" = " + user.getId() + " and user_.\"uID\" = policyowner_beneficiary.\"cbID\";";
    }

    public static String getSurveyorsByManager(User user) {
        return "select manager_surveyor.*, user_.* " +
                "from manager_surveyor, user_ " +
                "where manager_surveyor.\"managerID\" = " + user.getId() + " and user_.\"uID\" = manager_surveyor.\"pisID\";";
    }

    public static User getUserFromRes(ResultSet res) throws SQLException {
        if (!res.next())
            return null;
        return new User(res.getString("username"), res.getString("password"), res.getString("fullname"), "", "", "", Role.valueOf(res.getString("role")));
    }

    public static List<User> getUsersFromRes(ResultSet res) throws SQLException {
        List<User> users = new ArrayList<>();
        while (res.next()) {
            User user = new User(res.getString("username"), res.getString("password"), res.getString("fullname"), "", "", "", Role.valueOf(res.getString("role")));
            users.add(user);
        }
        return users;
    }
}
