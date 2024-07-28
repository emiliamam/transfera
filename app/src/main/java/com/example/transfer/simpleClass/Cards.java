package com.example.transfer.simpleClass;

public class Cards {

    String bank;
    String number;
    String name;
    boolean active;

    public Cards(String bank, String number, String name, boolean active) {
        this.bank = bank;
        this.number = number;
        this.name = name;
        this.active = active;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
