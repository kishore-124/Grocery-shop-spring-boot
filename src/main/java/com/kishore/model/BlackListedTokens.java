package com.kishore.model;

import javax.persistence.*;

@Entity
public class BlackListedTokens {

    public BlackListedTokens()
    {

    }

    public BlackListedTokens(int id, String token) {
        this.id = id;
        this.token = token;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
