package com.example.prova2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountAmministratore {
    @JsonProperty
    private String username;

    private String password;

    private String email;

    public AccountAmministratore(String email){
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
