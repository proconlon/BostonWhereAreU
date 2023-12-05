package com.example.bostonwhereareu.model.dto;


public class UserScoreDTO {
    private String userName;
    private int score;

    public UserScoreDTO(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public UserScoreDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
