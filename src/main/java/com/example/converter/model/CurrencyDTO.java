package com.example.converter.model;

public class CurrencyDTO {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyDTO() {}

    public CurrencyDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
