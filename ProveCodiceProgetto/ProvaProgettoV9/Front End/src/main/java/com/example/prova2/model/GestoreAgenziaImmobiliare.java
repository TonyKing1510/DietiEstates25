package com.example.prova2.model;

public class GestoreAgenziaImmobiliare extends Utente {
    public String partitaIva;

    public Agenzia agenziaAppartenente;

    private Admin adminAppartenente;

    private AccountGestore accountGestore;

    private boolean isAdmin = false;

    GestoreAgenziaImmobiliare(String nome, String cognome, String cf, int telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }

    public GestoreAgenziaImmobiliare() {
    }

    public Agenzia getAgenziaAppartenente() {
        return agenziaAppartenente;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
