package com.example.prova2.model;

public class AccountSemplice {
    private String username;

    private String password;

    private String email;

    public AccountSemplice(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getMail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
