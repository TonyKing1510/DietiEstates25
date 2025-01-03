package com.example.prova2.model;

import java.util.ArrayList;

public class Admin extends GestoreAgenziaImmobiliare {
    private ArrayList<GestoreAgenziaImmobiliare> gestoriCreati;

    private AccountAmministratore accountAmministratore;


    public Admin(String nome, String cognome,String societa, String partitaIva){
        this.nome=nome;
        this.cognome=cognome;
        this.partitaIva=partitaIva;
        this.agenziaAppartenente= new Agenzia(societa);
    }

    public void setAccountAmministratore(AccountAmministratore accountAmministratore) {
        this.accountAmministratore = accountAmministratore;
    }

    public AccountAmministratore getAccountAmministratore() {
        return accountAmministratore;
    }
}
