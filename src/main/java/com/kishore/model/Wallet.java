package com.kishore.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
public class Wallet {

    public Wallet() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Wallet(int id, Float totalAmount, Float amountAvailable, Float amountEntered, Float amountUsed, String currency) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.amountAvailable = amountAvailable;
        this.amountEntered = amountEntered;
        this.amountUsed = amountUsed;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Float amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Float getAmountEntered() {
        return amountEntered;
    }

    public void setAmountEntered(Float amountEntered) {
        this.amountEntered = amountEntered;
    }

    public Float getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(Float amountUsed) {
        this.amountUsed = amountUsed;
    }

    private Float totalAmount;

    private Float amountAvailable;



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @NotNull
    private Float amountEntered;

    private Float amountUsed;

    @NotNull
    private String currency;
}
