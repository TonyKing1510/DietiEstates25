package it.unina.webtech.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Cliente extends Utente {

    Cliente(String nome, String cognome, String cf, String telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }

    public Cliente() {}

    private Date dataRegistrazione;

    private Time horaRegistrazione;

    private ArrayList<Recensione> recensioniLasciate;

    private ArrayList<Visita> visitePrenotate;

    private AccountSemplice accountCliente;

    public AccountSemplice getAccountCliente() {
        return accountCliente;
    }

    public void setAccountCliente(AccountSemplice accountCliente) {
        this.accountCliente = accountCliente;
    }
}
