package com.example.prova2.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Cliente extends Utente {

    public Cliente(String nome, String cognome, String cf, String telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }

    private Date dataRegistrazione;

    private Time horaRegistrazione;

    private ArrayList<Recensione> recensioniLasciate;

    private ArrayList<Visita> visitePrenotate;

    private AccountSemplice accountCliente;

    public Cliente(String text, String text1, AccountSemplice accountSemplice) {
        this.nome = text;
        this.cognome = text1;
        this.accountCliente = accountSemplice;
    }

    public Cliente(String trim, String trim1, AccountSemplice accountSemplice, String text) {
        this.nome=trim;
        this.cognome=trim1;
        this.accountCliente = accountSemplice;
        this.setTelefono(text);
    }

    public AccountSemplice getAccountCliente() {
        return accountCliente;
    }

    public void setAccountCliente(AccountSemplice accountCliente) {
        this.accountCliente = accountCliente;
    }
}
