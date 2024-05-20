/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.PolicyOwner;

import java.sql.Date;

public class InsuranceCard {
    private Number id;
    private PolicyOwner owner;
    private Customer holder;
    private Date expiration_date;

    public Number getId() {
        return id;
    }

    public InsuranceCard(Number id) {
        this.id = id;
    }
}
