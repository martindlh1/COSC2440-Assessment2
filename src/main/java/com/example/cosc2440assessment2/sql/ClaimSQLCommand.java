package com.example.cosc2440assessment2.sql;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.user.User;

import static java.sql.Types.NULL;

public class ClaimSQLCommand {
    public static String getClaimsByUsername(String username) {
        return "select user_.\"uID\", claim.* from user_, claim where username = '" + username + "' and \"uID\" = claim.\"cID\";";
    }

    public static String getAllClaims() {
        return "select claim.*, user_.\"uID\", user_.fullname from claim, user_ where user_.\"uID\" = claim.\"cID\";";
    }

    public static String addClaim(Claim claim) {
        String sql = "insert into claim (\"cID\", \"cardID\", \"date\", \"exam_date\", \"amount\", \"status\") values (" + claim.getInsured() + ", " + claim.getCard().getId() + ", '" + claim.getDate() + "', '" + claim.getExam_date() + "', " + claim.getAmount() + ", '" + claim.getState() + "');";
        System.out.println(sql);
        return sql;
    }

    public static String deleteClaim(Claim claim) {
        return "delete from claim where \"id\" = " + claim.getId() + ";";
    }

    public static String updateClaim(Claim claim) {
        return "update claim set \"cardID\" = " + claim.getCard().getId() + ", \"cID\" = " + claim.getInsured() + ", \"date\" = '" + claim.getDate() + "', \"exam_date\" = " + (claim.getExam_date() == null ? null : "'" + claim.getExam_date() + "'" ) + ", \"amount\" = " + claim.getAmount() + ", \"status\" = '" + claim.getState() + "' where \"id\" = " + claim.getId() + ";";
    }

    public static String getDependentsClaimsByHolder(User user) {
        return "select claim.*, policyholder_dependent.\"cdID\", user_.\"uID\", user_.\"fullname\" " +
                "from claim, policyholder_dependent, user_ " +
                "where policyholder_dependent.\"cdID\" = claim.\"cID\" " +
                "and user_.\"uID\" = claim.\"cID\" " +
                "and policyholder_dependent.\"cphID\" = " + user.getId() + ";";
    }

    public static String getBeneficiariesClaimsByOwnerId(User user) {
        return "select claim.*, policyowner_beneficiary.\"cbID\", user_.\"uID\", user_.\"fullname\" " +
                "from claim, policyowner_beneficiary, user_ " +
                "where policyowner_beneficiary.\"cbID\" = claim.\"cID\" " +
                "and user_.\"uID\" = claim.\"cID\" " +
                "and policyowner_beneficiary.\"cpoID\" = " + user.getId() + ";";
    }
}
