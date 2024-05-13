/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.helper.IdGenerator;
import com.google.gson.Gson;

import java.util.Date;

public class InsuranceCard {
    private Number id;
    private Number holder;
    private Number policy_owner;
    private Date expiration;

    public InsuranceCard(Number holder, Number policy_owner, Date expiration) {
        this.id = IdGenerator.generate10digitId();
        this.holder = holder;
        this.policy_owner = policy_owner;
        this.expiration = expiration;
    }

    public Number getId() {
        return id;
    }

    @Override
    public String toString() {
        return ">\tid: " + id +
                "\n\tholder: " + holder +
                "\n\tpolicy_owner: " + policy_owner +
                "\n\texpiration: " + expiration;
    }

    public String toCustomerString() {
        return "\n\t\tid: " + id +
                "\n\t\tholder: " + holder +
                "\n\t\tpolicy_owner: " + policy_owner +
                "\n\t\texpiration: " + expiration;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this) + "\n";
    }
}
