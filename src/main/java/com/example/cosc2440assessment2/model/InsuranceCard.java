package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.PolicyOwner;

import java.sql.Date;

public class InsuranceCard {
    private Number id;
    private PolicyOwner owner;
    private Customer holder;
    private Date expiration_date;
}
