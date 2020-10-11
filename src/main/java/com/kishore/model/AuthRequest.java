package com.kishore.model;

import javax.persistence.Entity;


public class AuthRequest {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;

    public AuthRequest() {
    }

    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private String password;
}
