package com.kishore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import javax.persistence.*;

@Entity
public class Store {

    public Store()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    private String contact_number;

    public Store(int id, String name, String email, String contact_number, List<Product> products) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact_number = contact_number;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Product> products = new ArrayList<Product>();
}
