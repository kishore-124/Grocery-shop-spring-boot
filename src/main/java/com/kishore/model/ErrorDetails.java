package com.kishore.model;

public class ErrorDetails {

    public ErrorDetails() {

    }

    public ErrorDetails(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
