package com.example.cosc2440assessment2.service;

import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.repository.ClaimRepository;

import java.util.List;

public class ClaimService {
    private final ClaimRepository claimRepository = ClaimRepository.getInstance();
    public List<Claim> getClaimsByUsername(String username) {
        return claimRepository.getClaimsByUsername(username);
    }
}
