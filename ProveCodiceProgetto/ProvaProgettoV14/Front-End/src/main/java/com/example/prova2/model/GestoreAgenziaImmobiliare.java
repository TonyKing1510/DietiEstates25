package com.example.prova2.model;

public class GestoreAgenziaImmobiliare extends Utente {
    public String partitaIva;

    public Agenzia agenziaAppartenente;

    private GestoreAgenziaImmobiliare adminAppartenente;

    private AccountGestore accountGestore;

    private boolean isAdmin = false;

    public GestoreAgenziaImmobiliare(String nome, String cognome,String societa, String cf){
        this.nome=nome;
        this.cognome=cognome;
        this.setCf(cf);
        this.agenziaAppartenente= new Agenzia(societa);
    }


    public GestoreAgenziaImmobiliare(String nome, String cognome, String cf, String telefono,String via,String numeroCivico) {
        super(nome, cognome, cf, telefono, via, numeroCivico);
    }

    public GestoreAgenziaImmobiliare(Agenzia societa, String cf) {
        this.agenziaAppartenente = societa;
        this.setCf(cf);
    }

    public void setAdminAppartenente(GestoreAgenziaImmobiliare adminAppartenente) {
        this.adminAppartenente = adminAppartenente;
    }

    public GestoreAgenziaImmobiliare getAdminAppartenente() {
        return adminAppartenente;
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

    public void setAccountGestore(AccountGestore accountGestore) {
        this.accountGestore = accountGestore;
    }

    public AccountGestore getAccountGestore() {
        return accountGestore;
    }
}
