package com.example.cosc2440assessment2.service;

import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.repository.ClaimRepository;

import java.util.List;

public class ClaimService {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();
    public List<Claim> getClaimsByUsername(String username) {
        return claimRepository.getClaimsByUsername(username);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.getAllClaims();
    }

    public void addClaim(Claim claim) {
        claimRepository.addClaim(claim);
    }

    public void updateClaim(Claim claim) {
        claimRepository.updateClaim(claim);
    }

    public void deleteClaim(Claim claim) {
        claimRepository.deleteClaim(claim);
    }

    public List<Claim> getDependentsClaimsByHolder(User user) {
        return claimRepository.getDependentsClaimsByHolder(user);
    }

    public List<Claim> getBeneficiariesClaimsByOwner(User user) {
        return claimRepository.getBeneficiariesClaimsByOwner(user);
    }
}
