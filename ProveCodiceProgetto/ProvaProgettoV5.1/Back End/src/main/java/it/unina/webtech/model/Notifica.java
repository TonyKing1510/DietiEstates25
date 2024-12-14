package it.unina.webtech.model;

import it.unina.webtech.Not;

public class Notifica {
    private String nomeNotifica;

    private String descrizioneNotifica;

    private Agente agenteNotificato;

    private Utente utenteCheHaTriggeratoNotifica;

    public Notifica(String nomeNotifica,String descrizioneNotifica){
        this.nomeNotifica = nomeNotifica;
        this.descrizioneNotifica = descrizioneNotifica;
    }
}
