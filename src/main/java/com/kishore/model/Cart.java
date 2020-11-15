package com.kishore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    public Cart(int id, Product product, int cart_quantity) {
        this.id = id;
        this.product = product;
        this.cart_quantity = cart_quantity;

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
}

