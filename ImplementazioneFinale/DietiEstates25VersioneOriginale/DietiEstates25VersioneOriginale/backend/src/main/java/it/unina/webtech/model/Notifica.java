package it.unina.webtech.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notifica {

    private int idImmobile;

    private int idNotifica;

    private String nomeNotifica;

    private String descrizioneNotifica;

    private Agente agenteNotificato;

    private GestoreAgenziaImmobiliare gestoreNotificato;

    private Utente utenteNotificato;

    private Utente utenteCheHaTriggeratoNotifica;

    private CategoriaNotifica categoria;


    public Notifica(String titolo,String contenuto, int idNotifica, CategoriaNotifica categoria,int idImmobile) {
        this.nomeNotifica=titolo;
        this.descrizioneNotifica=contenuto;
        this.idNotifica=idNotifica;
        this.categoria=categoria;
        this.idImmobile=idImmobile;
    }
}
