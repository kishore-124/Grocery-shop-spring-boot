package com.kishore.model;

import javax.persistence.*;

@Entity
public class Role {

    public int getId() {
        return id;
    }

    public Role()
    {

    }


    public Role( int id, String name) {
        this.id = id;
        this.name = name;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
}
