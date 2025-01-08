package it.unina.webtech.model;

public class Notifica {
    private String nomeNotifica;

    private String descrizioneNotifica;

    private Agente agenteNotificato;

    private GestoreAgenziaImmobiliare gestoreNotificato;

    private Utente utenteNotificato;

    private Utente utenteCheHaTriggeratoNotifica;


    public Notifica(String titolo,String contenuto){
        this.nomeNotifica=titolo;
        this.descrizioneNotifica=contenuto;
    }

    public Notifica(){

    }


    public void setUtenteCheHaTriggeratoNotifica(Utente utenteCheHaTriggeratoNotifica) {
        this.utenteCheHaTriggeratoNotifica = utenteCheHaTriggeratoNotifica;
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
}
