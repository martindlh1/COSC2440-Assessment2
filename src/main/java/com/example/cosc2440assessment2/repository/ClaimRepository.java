package com.example.cosc2440assessment2.repository;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.singleton.Database;

import java.util.List;

public class ClaimRepository {
    private static ClaimRepository INSTANCE;
    private final Database database = Database.getInstance();

    public void addClaim(Claim claim) {
    }

    public void updateClaim(Claim claim) {
    }

    public void deleteClaim(Claim claim) {

    }

    public Claim getClaimByUsername(String username) {
        return null;
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
