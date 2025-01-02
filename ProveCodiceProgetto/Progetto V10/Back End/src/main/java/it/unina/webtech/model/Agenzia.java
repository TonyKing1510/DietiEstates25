package it.unina.webtech.model;

import java.util.ArrayList;

public class Agenzia {
    private String nomeAgenzia;

    private String località;

    private ArrayList<GestoreAgenziaImmobiliare> gestoriAgenzia;

    public String getNomeAgenzia() {
        return nomeAgenzia;
    }

    public void setNomeAgenzia(String nomeAgenzia) {
        this.nomeAgenzia = nomeAgenzia;
    }
}
