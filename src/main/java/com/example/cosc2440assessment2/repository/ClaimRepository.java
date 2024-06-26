/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.InsuranceCard;
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
        try {
            Statement statement = database.getDb().createStatement();
            statement.execute(ClaimSQLCommand.deleteClaim(claim));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Claim> getDependentsClaimsByHolder(User user) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(ClaimSQLCommand.getDependentsClaimsByHolder(user));
            List<Claim> claims = new ArrayList<>();
            while (res.next()) {
                claims.add(new Claim(res.getInt("id"), res.getDate("date"), res.getInt("cID"), res.getString("fullname"), res.getDate("exam_date"), new InsuranceCard(res.getInt("cardID")), null, res.getInt("amount"), null, ClaimState.valueOf(res.getString("status"))));
            }
            return claims;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Claim> getBeneficiariesClaimsByOwner(User user) {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(ClaimSQLCommand.getBeneficiariesClaimsByOwnerId(user));
            List<Claim> claims = new ArrayList<>();
            while (res.next()) {
                claims.add(new Claim(res.getInt("id"), res.getDate("date"), res.getInt("cID"),res.getString("fullname"), res.getDate("exam_date"), new InsuranceCard(res.getInt("cardID")), null, res.getInt("amount"), null, ClaimState.valueOf(res.getString("status"))));
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
                claims.add(new Claim(res.getInt("id"), res.getDate("date"), res.getInt("cID"), null, res.getDate("exam_date"), new InsuranceCard(res.getInt("cardID")), null, res.getInt("amount"), null, ClaimState.valueOf(res.getString("status"))));
            }
//            return new User(res.getString("username"), res.getString("password"), res.getString("fullName"), "", "", "", Role.valueOf(res.getString("role")));
            return claims;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Claim> getAllClaims() {
        try {
            Statement statement = database.getDb().createStatement();
            ResultSet res = statement.executeQuery(ClaimSQLCommand.getAllClaims());
            List<Claim> claims = new ArrayList<>();
            while (res.next()) {
                claims.add(new Claim(res.getInt("id"), res.getDate("date"), res.getInt("cID"), res.getString("fullname"), res.getDate("exam_date"), new InsuranceCard(res.getInt("cardID")), null, res.getInt("amount"), null, ClaimState.valueOf(res.getString("status"))));
            }
            return claims;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClaimRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ClaimRepository();
        return INSTANCE;
    }
}
