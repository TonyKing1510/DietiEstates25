package it.unina.webtech.model;


public class GestoreAgenziaImmobiliare extends Utente {
    private String partitaIva;

    private Agenzia agenziaAppartenente;

    private Admin adminAppartenente;

    private AccountGestore accountGestore;

    private boolean isAdmin;



    public boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


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

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public void setAgenziaAppartenente(Agenzia agenziaAppartenente) {
        this.agenziaAppartenente = agenziaAppartenente;
    }


}
