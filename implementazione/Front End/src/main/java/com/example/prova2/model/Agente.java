package com.example.prova2.model;

import java.util.ArrayList;

public class Agente extends Utente {
    private String partitaIva;

    private ArrayList<Recensione> recensioniAgente;

    private ArrayList<Notifica> notificheAgente;

    private ArrayList<Immobile> immobiliCaricati;

    private ArrayList<Visita> visiteAgente;

    private AccountAziendale accountAgente;

    Agente(String nome, String cognome, String cf, int telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }
}
