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

    public Claim(Number id, Date date, Customer insured, Date exam_date, InsuranceCard card, String[] docs, Number amount, BankInfo bankInfo, ClaimState state) {
        this.id = id;
        this.date = date;
        this.insured = insured;
        this.exam_date = exam_date;
        this.card = card;
        this.docs = docs;
        this.amount = amount;
        this.bankInfo = bankInfo;
        this.state = state;
    }

    public Number getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Customer getInsured() {
        return insured;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public InsuranceCard getCard() {
        return card;
    }

    public String[] getDocs() {
        return docs;
    }

    public Number getAmount() {
        return amount;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public ClaimState getState() {
        return state;
    }
}
