package com.example.prova2.model;

import java.util.ArrayList;

public class Agente extends Utente {
    private String partitaIva;

    private ArrayList<Recensione> recensioniAgente;

    private ArrayList<Notifica> notificheAgente;

    private ArrayList<Immobile> immobiliCaricati;

    private ArrayList<Visita> visiteAgente;

    private AccountAziendale accountAgente;

    private GestoreAgenziaImmobiliare gestoreRiferimento;


    public Agente(String nome, String cognome, String cf, String telefono, String indirizzo, String numeroCivico) {
        super(nome, cognome, cf, telefono, indirizzo,numeroCivico);
    }

    public AccountAziendale getAccountAgente() {
        return accountAgente;
    }

    public void setAccountAgente(AccountAziendale accountAgente) {
        this.accountAgente = accountAgente;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public GestoreAgenziaImmobiliare getGestoreRiferimento() {
        return gestoreRiferimento;
    }

    public void setGestoreRiferimento(GestoreAgenziaImmobiliare gestoreRiferimento) {
        this.gestoreRiferimento = gestoreRiferimento;
    }
}
