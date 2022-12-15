package com.cip.kingofquiz.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "isLoggedIn")
    private Boolean isLoggedIn;
    @ColumnInfo(name = "score")
    private int score ;
    @ColumnInfo(name = "settingID")
    private int settingID;
    @ColumnInfo(name = "gameIDs")
    private String gameIDs;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = "";
        this.username = "";
        this.phone = "";
        this.isLoggedIn = false;
        this.score = 0;
        this.settingID = 0;
        this.gameIDs = "";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getGameIDs() {
        return gameIDs;
    }

    public void setGameIDs(String gameIDs) {
        this.gameIDs = gameIDs;
    }
}
