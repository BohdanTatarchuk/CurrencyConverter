package com.example.converter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Currency {

    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    protected Currency() {}

    public Currency (String id, String name) {
        this.id = id;
        this.name = name;
    }
}
