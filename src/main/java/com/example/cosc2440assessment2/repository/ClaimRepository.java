package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.singleton.Database;
import com.example.cosc2440assessment2.sql.ClaimSQLCommand;
import com.example.cosc2440assessment2.sql.UserSQLCommand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClaimRepository {
    private static ClaimRepository INSTANCE;
    private final Database database = Database.getInstance();

    public void addClaim(Claim claim) {
        try {
            Statement statement = database.getDb().createStatement();
            statement.execute(ClaimSQLCommand.addClaim(claim));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClaim(Claim claim) {
        try {
            Statement statement = database.getDb().createStatement();
            statement.execute(ClaimSQLCommand.updateClaim(claim));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClaim(Claim claim) {

    }

    public List<Claim> getDependentsClaimsByHolder(User user) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(ClaimSQLCommand.getDependentsClaimsByHolder(user));
            List<Claim> claims = new ArrayList<>();
            while (res.next()) {
                claims.add(new Claim(res.getInt("id"), res.getDate("date"), null, null, null, null, null, null, ClaimState.APPROVED));
            }
            return claims;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Claim> getClaimsByUsername(String username) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(ClaimSQLCommand.getClaimsByUsername(username));
            List<Claim> claims = new ArrayList<>();
            while (res.next()) {
                claims.add(new Claim(res.getInt("id"), res.getDate("date"), null, null, null, null, null, null, ClaimState.APPROVED));
            }
//            return new User(res.getString("username"), res.getString("password"), res.getString("fullName"), "", "", "", Role.valueOf(res.getString("role")));
            return claims;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Claim> getAllClaims() {
        return null;
    }

    public static ClaimRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ClaimRepository();
        return INSTANCE;
    }
}
