package com.example.transfer.simpleClass;

public class PaymantCard {

    private final int id;
    private final int summ;
    private final int numberCard;
    private final int name;
    private final String time;
    private String succes;
    public PaymantCard(int id, int summ, int numberCard, int name, String time) {
        this.id = id;
        this.summ = summ;
        this.numberCard = numberCard;
        this.name = name;
        this.time = time;
    }
}
