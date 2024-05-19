package com.example.converter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String login;

    private String password;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    protected User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
