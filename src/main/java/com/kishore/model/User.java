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

    @OneToOne(cascade = {CascadeType.MERGE})
    private Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @OneToOne(cascade = {CascadeType.MERGE})
    private Role role;


    public User() {

    }

    private Date Create_at;

    private Date updated_at;


    @Override
    public int read() throws IOException {
        return 0;
    }

    public User(Wallet wallet, Role role, Date create_at, Date updated_at, int id, String email, String userName, String password, String phone_number, List<Order> orders, List<Address> addresses, Cart cart, String reset_password_token) {
        this.wallet = wallet;
        this.role = role;
        this.Create_at = create_at;
        this.updated_at = updated_at;
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phone_number = phone_number;
        this.orders = orders;
        this.addresses = addresses;
        this.cart = cart;
        this.reset_password_token = reset_password_token;
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
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String userName;

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
    @Column(unique = true)
    private String phone_number;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Date getCreate_at() {
        return Create_at;
    }

    public void setCreate_at(Date create_at) {
        Create_at = create_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToOne
    private Cart cart;

    public String getReset_password_token() {
        return reset_password_token;
    }

    public void setReset_password_token(String reset_password_token) {
        this.reset_password_token = reset_password_token;
    }

    private String reset_password_token;
}
