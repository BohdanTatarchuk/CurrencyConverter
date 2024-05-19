package com.example.converter.model;

public class CurrencyDTO {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyDTO() {}

    public CurrencyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
