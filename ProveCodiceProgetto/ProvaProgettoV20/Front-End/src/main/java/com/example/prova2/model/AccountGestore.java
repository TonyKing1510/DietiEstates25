package com.example.prova2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountGestore {

    @JsonProperty
    private String username;

    private String password;

    private String email;

    public AccountGestore(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountGestore(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountGestore(String mail, String password) {
        this.email = mail;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
