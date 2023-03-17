package org.example;

import java.math.BigDecimal;

public class Account {
    private int id;
    private double balance;
    private String description;

    public Account() {

    }
    public Account(int id, double balance, String description) {
        this.id = id;
        this.balance = balance;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
