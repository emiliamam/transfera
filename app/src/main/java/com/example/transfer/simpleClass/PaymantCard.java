package com.example.transfer.simpleClass;

public class PaymantCard {

    private final String id;
    private final String summ;
    private final String numberCard;
    private final String name;
    private final String time;
    private String status;
    public PaymantCard(String id, String summ, String numberCard, String name, String time, String status) {
        this.id = id;
        this.summ = summ;
        this.numberCard = numberCard;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getSumm() {
        return summ;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
