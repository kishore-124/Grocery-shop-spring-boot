package com.kishore.model;

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


    @OneToOne
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Cart(int id, Product product, int cart_quantity, Date created_at, Date updated_at) {
        this.id = id;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

