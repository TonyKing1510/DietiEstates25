package com.example.prova2.model;

public class AccountSemplice {
    private String username;

    private String password;

    private String email;

    public AccountSemplice(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountSemplice(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }



    public String getMail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
