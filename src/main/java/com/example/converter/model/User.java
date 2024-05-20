package com.example.converter.model;

import jakarta.persistence.*;

@Entity
@Table
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
