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

    public Order()
    {

    }

    public Order(int id, Date delivery_date_time, String order_no, Date order_date, float amount, String status, String delivery_mode, User user, List<Product> product) {
        this.id = id;
        this.delivery_date_time = delivery_date_time;
        this.order_no = order_no;
        this.order_date = order_date;
        this.amount = amount;
        this.status = status;
        this.delivery_mode = delivery_mode;
        this.user = user;
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "order")
    private List<Product> product = new ArrayList<Product>();
}
