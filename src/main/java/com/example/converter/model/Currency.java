package com.example.converter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Currency", schema = "public")
public class Currency {

    @Id
    @Column(name = "currency_id")
    private String id;

    @Column(name = "currency_name")
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
//卍 ZOV 卍