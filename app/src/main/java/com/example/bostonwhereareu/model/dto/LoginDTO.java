package com.example.bostonwhereareu.model.dto;


public class LoginDTO {
    private String userName;

    public LoginDTO(String userName) {
        this.userName = userName;
    }

    public LoginDTO() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
