package com.example.prova2.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Cliente extends Utente {

    public Cliente(String nome, String cognome, String cf, int telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }

    private Date dataRegistrazione;

    private Time horaRegistrazione;

    private ArrayList<Recensione> recensioniLasciate;

    private ArrayList<Visita> visitePrenotate;

    private AccountSemplice accountCliente;


}
