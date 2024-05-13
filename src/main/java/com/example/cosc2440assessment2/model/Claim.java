/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.model;

import com.example.cosc2440assessment2.helper.IdGenerator;
import com.example.cosc2440assessment2.repository.CustomerRepository;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Date;

public class Claim {
    private final Number id;
    private final Date date;
    private Number card_number;
    private Number insured;
    private Date exam_date;
    private String[] doc;
    private Number amount;
    private BankInfo bankInfo;

    public Claim(Customer insured, Date exam_date, String[] doc, Number amount, BankInfo bankInfo) {
        this.id = IdGenerator.generate10digitId();
        this.date = new Date();
        this.insured = insured.getId();
        this.exam_date = exam_date;
        this.card_number = insured.getInsurance_card();
        this.doc = doc;
        this.amount = amount;
        this.bankInfo = bankInfo;
    }

    public Number getId() {
        return this.id;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public Number getInsured() {
        return insured;
    }

    public void setInsured(Number insured) {
        this.insured = insured;
    }

    public Number getCard_number() {
        return card_number;
    }

    public void setCard_number(Number card_number) {
        this.card_number = card_number;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public void setDoc(String[] doc) {
        this.doc = doc;
    }

    @Override
    public String toString() {
        return ">\tid: " + id +
                "\n\tdate: " + date +
                "\n\tinsured: " + insured +
                "\n\tcard_number: " + card_number +
                "\n\texam_date: " + exam_date +
                "\n\tdoc: " + Arrays.toString(doc) +
                "\n\tamount: " + amount +
                "\n\tbankInfo: " + (bankInfo == null ? "null" : bankInfo.getNumber());
    }

    public String toCustomerString() {
        return "\n\t>\tid: " + id +
                "\n\t\tdate: " + date +
                "\n\t\tinsured: " + insured +
                "\n\t\tcard_number: " + card_number +
                "\n\t\texam_date: " + exam_date +
                "\n\t\tdoc: " + Arrays.toString(doc) +
                "\n\t\tamount: " + amount +
                "\n\t\tbankInfo: " + bankInfo.getNumber();
    }

    public String toDetailedString() {
        return ">\tid: " + id +
                "\n\tdate: " + date +
                "\n\tinsured: " + CustomerRepository.getInstance().getOne(insured).toClaimString() +
                "\n\tcard_number: " + card_number +
                "\n\texam_date: " + exam_date +
                "\n\tdoc: " + Arrays.toString(doc) +
                "\n\tamount: " + amount +
                "\n\tbankInfo:" + (bankInfo == null ? "null" :
                "\n\t\t" + bankInfo.getBank() +
                "\n\t\t" + bankInfo.getName() +
                "\n\t\t" + bankInfo.getNumber());
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this) + "\n";
    }
}
