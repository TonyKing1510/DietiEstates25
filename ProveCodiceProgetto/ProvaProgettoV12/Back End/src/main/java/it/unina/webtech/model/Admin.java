package it.unina.webtech.model;

import java.util.ArrayList;

public class Admin extends GestoreAgenziaImmobiliare {

    public Admin() {}

    private ArrayList<GestoreAgenziaImmobiliare> gestoriCreati;

    private AccountAmministratore accountAmministratore;


    Admin(String nome, String cognome, String cf, int telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }

    public void setAccountAmministratore(AccountAmministratore accountAmministratore) {
        this.accountAmministratore = accountAmministratore;
    }

    public AccountAmministratore getAccountAmministratore() {
        return accountAmministratore;
    }

}
