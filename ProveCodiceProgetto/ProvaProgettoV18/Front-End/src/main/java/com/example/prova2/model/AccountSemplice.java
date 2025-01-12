package com.example.prova2.model;

public class AccountSemplice {
    private String username;

    private String password;

    private String email;

    public AccountSemplice(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getMail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
