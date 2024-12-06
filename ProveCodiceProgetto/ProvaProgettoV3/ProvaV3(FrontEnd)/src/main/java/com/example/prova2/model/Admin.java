package com.example.prova2.model;
import java.util.ArrayList;

public class Admin extends Utente{
    private String partitaIva;

    private ArrayList<GestoreAgenziaImmobiliare> gestoriCreati = new ArrayList<>();

    Admin(){
        super();
    }

}
