package it.unina.webtech.model;

import java.util.ArrayList;

public class Admin extends GestoreAgenziaImmobiliare {

    public Admin() {}

    private ArrayList<GestoreAgenziaImmobiliare> gestoriCreati;

    private AccountAmministratore accountAmministratore;


    public Admin(String nome, String cognome, String cf, String telefono, String indirizzo,String numeroCivico) {
        super(nome, cognome, cf, telefono, indirizzo,numeroCivico);
    }

    public void setAccountAmministratore(AccountAmministratore accountAmministratore) {
        this.accountAmministratore = accountAmministratore;
    }

    public AccountAmministratore getAccountAmministratore() {
        return accountAmministratore;
    }

    @Override
    public String getCf() {
        return super.getCf();
    }

    @Override
    public void setCf(String cf) {
        super.setCf(cf);
    }
}
