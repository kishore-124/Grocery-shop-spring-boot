package com.kishore.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
public class Role {

    public int getId() {
        return id;
    }

    public Role()
    {

    }

    public Role(int id, String name, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    @NotNull
    private String name;

    private Date created_at;

    private Date updated_at;
}
