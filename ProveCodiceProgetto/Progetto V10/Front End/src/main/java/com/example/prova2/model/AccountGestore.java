package com.example.prova2.model;

public class AccountGestore {
    private String username;

    private String password;

    private String email;

    public AccountGestore(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
