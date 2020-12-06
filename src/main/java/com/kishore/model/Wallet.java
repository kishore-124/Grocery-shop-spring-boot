package com.kishore.model;

import com.sun.istack.NotNull;
import java.util.*;
import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
public class Wallet {

    public Wallet() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Wallet(int id, Float totalAmount, Float amountAvailable, Float amountEntered, Float amountUsed, String currency, Date created_at, Date updated_at) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.amountAvailable = amountAvailable;
        this.amountEntered = amountEntered;
        this.amountUsed = amountUsed;
        this.currency = currency;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    private Date created_at;

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    private Date updated_at;
}
