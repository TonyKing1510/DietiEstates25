package it.unina.webtech.model;

public class Notifica {
    private String nomeNotifica;

    private String descrizioneNotifica;

    private Agente agenteNotificato;

    private Utente utenteCheHaTriggeratoNotifica;

    public Notifica(String nomeNotifica,String descrizioneNotifica){
        this.nomeNotifica = nomeNotifica;
        this.descrizioneNotifica = descrizioneNotifica;
    }

    public String getNomeNotifica() {
        return nomeNotifica;
    }

    public String getDescrizioneNotifica() {
        return descrizioneNotifica;
    }
}
