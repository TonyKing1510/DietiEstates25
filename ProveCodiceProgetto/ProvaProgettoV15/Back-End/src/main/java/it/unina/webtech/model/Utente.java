package it.unina.webtech.model;

import java.util.ArrayList;

public class Utente {
    private String nome;

    private String cognome;

    private String cf;

    private String telefono;

    private String via;

    private String numeroCivico;

    private ArrayList<Notifica> notificheLasciate;

    private ArrayList<Ricerca> ricercheFatte;


    Utente(String nome, String cognome, String cf, String telefono, String indirizzo,String numeroCivico) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.telefono = telefono;
        this.via = indirizzo;
        this.numeroCivico = numeroCivico;
    }

    Utente(String nome, String cognome, String cf, String telefono, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.telefono = telefono;
        this.via = indirizzo;
    }

    public Utente() {
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getCf() {
        return cf;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
