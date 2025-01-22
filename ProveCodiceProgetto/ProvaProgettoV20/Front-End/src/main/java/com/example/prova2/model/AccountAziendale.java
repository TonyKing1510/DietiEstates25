package com.example.prova2.model;

public class AccountAziendale {
    private String username;

    private String password;

    private String email;

    public AccountAziendale(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountAziendale(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
