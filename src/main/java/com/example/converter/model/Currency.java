package com.example.converter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Currency {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    protected Currency() {}

    public Currency (int id, String name) {
        this.id = id;
        this.name = name;
    }
}
