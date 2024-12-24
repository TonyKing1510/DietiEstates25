package it.unina.webtech.model;

import java.util.ArrayList;

public class Utente {
    private String nome;

    private String cognome;

    private String cf;

    private int telefono;

    private String indirizzo;

    private ArrayList<Notifica> notificheLasciate;

    private ArrayList<Ricerca> ricercheFatte;


    Utente(String nome, String cognome, String cf, int telefono, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
    }
}
