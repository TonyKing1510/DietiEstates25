package com.example.prova2.model;

import java.util.ArrayList;

public class Utente {
    public String nome;

    public String cognome;

    private String cf;

    private String telefono;

    private String via;

    private String numeroCivico;

    private ArrayList<Notifica> notificheLasciate;

    private ArrayList<Ricerca> ricercheFatte;


    public Utente(String nome, String cognome, String cf, String telefono, String via,String numeroCivico) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.telefono = telefono;
        this.via = via;
        this.numeroCivico = numeroCivico;
    }


    public Utente() {
    }

    public Utente(String nome, String cognome, String cf, String telefono, String indirizzo) {
        this.nome  = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.telefono = telefono;
        this.via = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getVia() {
        return via;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
