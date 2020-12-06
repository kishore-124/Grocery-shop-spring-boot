package com.kishore.model;

import java.util.*;
import javax.persistence.*;

@Entity
public class Address {

    @Id
    private int id;

    private String door_number;

    private String street_name;

    public Address() {

    }

    public Address(int id, String door_number, String street_name, String village_name, String district, String state, String country, String pin_code, Date created_at, Date updated_at, User user) {
        this.id = id;
        this.door_number = door_number;
        this.street_name = street_name;
        this.village_name = village_name;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pin_code = pin_code;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoor_number() {
        return door_number;
    }

    public void setDoor_number(String door_number) {
        this.door_number = door_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

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

    private String village_name;

    private String district;

    private String state;

    private String country;

    private String pin_code;

    private Date created_at;

    private Date updated_at;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;

}