package com.example.cosc2440assessment2.sql;

import com.example.cosc2440assessment2.model.Claim;

public class ClaimSQLCommand {
    public static String getClaimsByUsername(String username) {
        return "select user_.\"uID\", claim.* from user_, claim where username = '" + username + "' and \"uID\" = claim.\"cID\";";
    }

    public static String addClaim(Claim claim) {
        String sql = "insert into claim (\"cID\", \"cardID\", \"date\", \"exam_date\", \"amount\", \"status\") values (" + claim.getInsured() + ", " + claim.getCard().getId() + ", '" + claim.getDate() + "', '" + claim.getExam_date() + "', " + claim.getAmount() + ", '" + claim.getState() + "');";
        System.out.println(sql);
        return sql;
    }

    public static String updateClaim(Claim claim) {
        return "update claim set \"cardID\" = " + claim.getCard().getId() + ", \"cID\" = " + claim.getInsured() + ", \"date\" = '" + claim.getDate() + "', \"exam_date\" = '" + claim.getExam_date() + "', \"amount\" = " + claim.getAmount() + ", \"status\" = '" + claim.getState() + "' where \"id\" = " + claim.getId() + ";";
    }
}
