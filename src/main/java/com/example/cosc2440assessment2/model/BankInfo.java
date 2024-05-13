/**
 * @author <Martin Delahousse - s4034308>
 */

package com.example.cosc2440assessment2.model;

public class BankInfo {
    private String bank;
    private String name;
    private Number number;

    public BankInfo() {
        this.bank = null;
        this.name = null;
        this.number = null;
    }

    public BankInfo(String bank, String name, Number number) {
        this.bank = bank;
        this.name = name;
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}
