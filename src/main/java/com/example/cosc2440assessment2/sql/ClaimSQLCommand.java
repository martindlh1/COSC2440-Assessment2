package com.example.cosc2440assessment2.sql;

import com.example.cosc2440assessment2.model.Claim;

public class ClaimSQLCommand {
    public static String getClaimsByUsername(String username) {
        return "select user_.\"uID\", claim.* from user_, claim where username = '" + username + "' and \"uID\" = claim.\"cID\";";
    }

    public static String addClaim(Claim claim) {
        return null;
    }
}
