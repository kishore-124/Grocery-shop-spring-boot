package com.kishore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;


@Entity
public class Cart {

    public Cart() {

    }

    public Cart(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @OneToMany
    private List<User> users = new ArrayList<User>();

    @JsonIgnore
    @OneToMany
   private List<Product> products = new ArrayList<Product>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Cart(int id, List<User> users, List<Product> products, int cart_quantity, Date created_at, Date updated_at) {
        this.id = id;
        this.users = users;
        this.products = products;
        this.cart_quantity = cart_quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

    private int cart_quantity;

    private Date created_at;

    private Date updated_at;
}

