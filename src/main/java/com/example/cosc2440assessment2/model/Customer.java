/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.helper.IdGenerator;
import com.example.cosc2440assessment2.repository.ClaimRepository;
import com.example.cosc2440assessment2.repository.InsuranceCardRepository;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Number id;
    private String full_name;
    private Number insurance_card;
    private CustomerType type;
    private List<Number> claims;

    public Customer(String full_name, Number insurance_card, CustomerType type) {
        this.id = IdGenerator.generate7digitId();
        this.full_name = full_name;
        this.insurance_card = insurance_card;
        this.type = type;
        this.claims = new ArrayList<>();
    }

    public Number getId() {
        return id;
    }

    public void addClaim(Claim claim) {
        claims.add(claim.getId());
    }

    public void removeClaim(Claim claim) {
        claims.remove(claim.getId());
    }

    public Number getInsurance_card() {
        return insurance_card;
    }

    public void setInsurance_card(Number insurance_card) {
        this.insurance_card = insurance_card;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return ">\tid: " + id +
                "\n\tfull_name: " + full_name +
                "\n\tinsurance_card: " + insurance_card +
                "\n\ttype: " + type +
                "\n\tclaims: " + claims;
    }

    public String toClaimString() {
        return "\n\t\tid: " + id +
                "\n\t\tfull_name: " + full_name +
                "\n\t\tinsurance_card: " + insurance_card +
                "\n\t\ttype: " + type +
                "\n\t\tclaims: " + claims;
    }

    public String toDetailedString() {
        List<String> detailedClaims = new ArrayList<>();
        for (Number claimId : claims) {
            Claim claim = ClaimRepository.getInstance().getOne(claimId);
            detailedClaims.add(claim.toCustomerString());
        }
        if (!detailedClaims.isEmpty())
            detailedClaims.add("\n\t");
        return ">\tid: " + id +
                "\n\tfull_name: " + full_name +
                "\n\tinsurance_card: " + InsuranceCardRepository.getInstance().getOne(insurance_card).toCustomerString() +
                "\n\ttype: " + type +
                "\n\tclaims: " + detailedClaims;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this) + "\n";
    }
}
