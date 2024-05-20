package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.model.user.Customer;

import java.sql.Date;

public class Claim {
    private Number id;
    private Date date;
    private Number insured;
    private Date exam_date;
    private InsuranceCard card;
    private String[] docs;
    private Number amount;
    private BankInfo bankInfo;
    private ClaimState state;

    public Claim(Number id, Date date, Number insured, Date exam_date, InsuranceCard card, String[] docs, Number amount, BankInfo bankInfo, ClaimState state) {
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

    public Claim(Date date, Number insured, Date exam_date, InsuranceCard card, String[] docs, Number amount, BankInfo bankInfo, ClaimState state) {
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

    public Number getInsured() {
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

    public void setId(Number id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInsured(Number insured) {
        this.insured = insured;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    public void setCard(InsuranceCard card) {
        this.card = card;
    }

    public void setDocs(String[] docs) {
        this.docs = docs;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public void setState(ClaimState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return date + " - " +
                (insured == null ? "N/A" : insured) + " - " +
                (amount == null ? "N/A" : amount.toString()) + " - " +
                (exam_date == null ? "N/A" : exam_date) + " - " +
                state;
    }
}
