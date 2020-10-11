package com.kishore.model;

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
    private Role role;

    public User() {

    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public User(Role role, int id, String email, String userName, String password, String phone_number, byte[] data) {
        this.role = role;
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phone_number = phone_number;
        this.data = data;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    private String email;

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

    private String password;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    private String phone_number;
    private byte[] data;
}
