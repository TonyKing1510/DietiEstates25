package it.unina.webtech.model;

public class GestoreAgenziaImmobiliare extends Utente {
    private String partitaIva;

    private Agenzia agenziaAppartenente;

    private Admin adminAppartenente;

    private AccountGestore accountGestore;

    GestoreAgenziaImmobiliare(String nome, String cognome, String cf, int telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }
}
