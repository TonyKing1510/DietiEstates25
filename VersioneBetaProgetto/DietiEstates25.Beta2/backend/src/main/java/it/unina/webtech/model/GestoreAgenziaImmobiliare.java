package it.unina.webtech.model;


import lombok.Getter;
import lombok.Setter;

@Setter
public class GestoreAgenziaImmobiliare extends Utente {

    @Getter
    private Agenzia agenziaAppartenente;

    @Getter
    private AccountGestore accountGestore;

    private boolean isAdmin;


    public boolean getisAdmin() {
        return isAdmin;
    }

    public GestoreAgenziaImmobiliare() {}


}
