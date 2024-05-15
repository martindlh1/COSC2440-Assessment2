package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.model.user.Customer;

import java.sql.Date;

public class Claim {
    private Number id;
    private Date date;
    private Customer insured;
    private Date exam_date;
    private InsuranceCard card;
    private String[] docs;
    private Number amount;
    private BankInfo bankInfo;
    private ClaimState state;
}
