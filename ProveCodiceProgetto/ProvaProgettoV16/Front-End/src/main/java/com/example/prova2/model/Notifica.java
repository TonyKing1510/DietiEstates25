package com.example.prova2.model;

public class Notifica {
    private String nomeNotifica;

    private String descrizioneNotifica;

    private Agente agenteNotificato;

    private GestoreAgenziaImmobiliare gestoreNotificato;

    private Utente utenteNotificato;

    private Utente utenteCheHaTriggeratoNotifica;

    private int idNotifica;

    public Notifica() {}

    // Costruttore
    public Notifica(String nomeNotifica, String descrizioneNotifica,int idNotifica) {
        this.nomeNotifica = nomeNotifica;
        this.descrizioneNotifica = descrizioneNotifica;
        this.idNotifica = idNotifica;
    }

    public Agente getAgenteNotificato() {
        return agenteNotificato;
    }

    public String getDescrizioneNotifica() {
        return descrizioneNotifica;
    }

    public String getNomeNotifica() {
        return nomeNotifica;
    }



    public void setNomeNotifica(String nomeNotifica) {
        this.nomeNotifica = nomeNotifica;
    }

    public void setDescrizioneNotifica(String descrizioneNotifica) {
        this.descrizioneNotifica = descrizioneNotifica;
    }

    public Utente getUtenteCheHaTriggeratoNotifica() {
        return utenteCheHaTriggeratoNotifica;
    }

    public void setAgenteNotificato(Agente agenteNotificato) {
        this.agenteNotificato = agenteNotificato;
    }

    // Getter e Setter
    public int getIdNotifica() {
        return idNotifica;
    }
}
