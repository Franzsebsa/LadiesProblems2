package com.example.franz.ladiesproblems.modelo;

/**
 * Created by franz on 03/11/2017.
 */

public class user {


    private String user;
    private String password;


    public user() {
    }

    public user(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
