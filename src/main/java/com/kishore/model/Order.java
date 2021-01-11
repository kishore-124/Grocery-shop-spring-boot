package com.kishore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date delivery_date_time;

    public Order() {

    }

    public Order(int id, Date delivery_date_time, String order_no, Date order_date, float amount, String status, String delivery_mode, User user, Date created_at, Date updated_at, Product product) {
        this.id = id;
        this.delivery_date_time = delivery_date_time;
        this.order_no = order_no;
        this.order_date = order_date;
        this.amount = amount;
        this.status = status;
        this.delivery_mode = delivery_mode;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDelivery_date_time() {
        return delivery_date_time;
    }

    public void setDelivery_date_time(Date delivery_date_time) {
        this.delivery_date_time = delivery_date_time;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery_mode() {
        return delivery_mode;
    }

    public void setDelivery_mode(String delivery_mode) {
        this.delivery_mode = delivery_mode;
    }

    private String order_no;

    private Date order_date;

    private float amount;

    private String status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String delivery_mode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    private Date created_at;

    private Date updated_at;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonIgnore
    @ManyToOne
    private Product product;
}
