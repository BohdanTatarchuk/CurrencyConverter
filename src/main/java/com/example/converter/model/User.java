package com.example.converter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userc", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "user_id")
    private int id;

    @Column (name = "login")
    private String login;

    @Column (name = "password")
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
