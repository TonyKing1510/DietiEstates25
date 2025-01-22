package it.unina.webtech.model;

import java.util.ArrayList;

public class Immobile {
    private String descrizioneImmobile;
    private ArrayList<Visita> visiteImmobile;


    private PosizioneGeograficaImmobile posizioneGeografica;
    private DettagliStrutturaliImmobile dettagliStrutturali;
    private DettagliVenditaImmobile dettagliVendita;



    private classe_energetica classeEnergetica;

    public String getDescrizioneImmobile() {
        return descrizioneImmobile;
    }

    public classe_energetica getClasseEnergetica() {
        return classeEnergetica;
    }

    public DettagliStrutturaliImmobile getDettagliStrutturali() {
        return dettagliStrutturali;
    }

    public DettagliVenditaImmobile getDettagliVendita() {
        return dettagliVendita;
    }

    public PosizioneGeograficaImmobile getPosizioneGeografica() {
        return posizioneGeografica;
    }

    public void setClasseEnergetica(classe_energetica classeEnergetica) {
        this.classeEnergetica = classeEnergetica;
    }

    public void setDescrizioneImmobile(String descrizioneImmobile) {
        this.descrizioneImmobile = descrizioneImmobile;
    }

    public void setDettagliStrutturali(DettagliStrutturaliImmobile dettagliStrutturali) {
        this.dettagliStrutturali = dettagliStrutturali;
    }

    public void setDettagliVendita(DettagliVenditaImmobile dettagliVendita) {
        this.dettagliVendita = dettagliVendita;
    }

    public void setPosizioneGeografica(PosizioneGeograficaImmobile posizioneGeografica) {
        this.posizioneGeografica = posizioneGeografica;
    }


    public void setVisiteImmobile(ArrayList<Visita> visiteImmobile) {
        this.visiteImmobile = visiteImmobile;
    }
}
