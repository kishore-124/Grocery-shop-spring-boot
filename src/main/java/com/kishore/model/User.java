package com.kishore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Entity
public class User extends InputStream {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    private Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    private Role role;

    public User() {

    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public User(Wallet wallet, Role role, int id, String email, String userName, String password, String phone_number, byte[] data, List<Order> orders, Cart cart) {
        this.wallet = wallet;
        this.role = role;
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phone_number = phone_number;
        this.data = data;
        this.orders = orders;
        this.cart = cart;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @NotNull
    private String email;

    @NotNull
    @Column(unique = true)
    private String userName;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull
    private String password;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @NotNull
    private String phone_number;

    private byte[] data;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;



    @OneToOne
    private Cart cart;
}
