package com.example.prova2.model;

public class Notifica {
    private String nomeNotifica;

    private String descrizioneNotifica;

    private Agente agenteNotificato;

    private Utente utenteCheHaTriggeratoNotifica;

    public Agente getAgenteNotificato() {
        return agenteNotificato;
    }

    public String getDescrizioneNotifica() {
        return descrizioneNotifica;
    }

    public String getNomeNotifica() {
        return nomeNotifica;
    }

    public Utente getUtenteCheHaTriggeratoNotifica() {
        return utenteCheHaTriggeratoNotifica;
    }
}
