package com.example.prova2.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Ricerca {

    private String titoloRicerca;

    private Date dataRicerca;

    private Time horaRicerca;

    private int prezzoMinimo;

    private int prezzoMaximo;

    private int numeroStanze;

    private String classeEnergetica;

    private String localitaRicercata;

    private String viaRicercata;

    private ArrayList<Utente> utentiCheHannoFattoRicerca;
}
