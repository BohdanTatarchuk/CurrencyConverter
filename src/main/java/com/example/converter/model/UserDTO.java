package com.example.converter.model;

public class UserDTO {

    private int id;

    private String login;

    public int getId() {
        return id;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO() {}

    public UserDTO(int id, String login){
        this.id = id;
        this.login = login;
    }
}
