package it.unina.webtech.model;

public class DettagliStrutturaliImmobile {
    private Integer superficie;
    private Integer dimensione;
    private Integer numeroStanze;
    private Integer numeroBagni;
    private Integer numeroCucine;
    private Integer numeroSoggiorni;
    private Integer piano;
    private Integer numeroPiani;



    public Integer getSuperficie() {
        return superficie;
    }

    public Integer getDimensione() {
        return dimensione;
    }

    public Integer getNumeroStanze() {
        return numeroStanze;
    }

    public Integer getPiano() {
        return piano;
    }

    public Integer getNumeroBagni() {
        return numeroBagni;
    }

    public Integer getNumeroCucine() {
        return numeroCucine;
    }

    public Integer getNumeroPiani() {
        return numeroPiani;
    }

    public Integer getNumeroSoggiorni() {
        return numeroSoggiorni;
    }

    public void setDimensione(Integer dimensione) {
        this.dimensione = dimensione;
    }

    public void setNumeroBagni(Integer numeroBagni) {
        this.numeroBagni = numeroBagni;
    }

    public void setNumeroCucine(Integer numeroCucine) {
        this.numeroCucine = numeroCucine;
    }

    public void setNumeroPiani(Integer numeroPiani) {
        this.numeroPiani = numeroPiani;
    }

    public void setNumeroSoggiorni(Integer numeroSoggiorni) {
        this.numeroSoggiorni = numeroSoggiorni;
    }

    public void setNumeroStanze(Integer numeroStanze) {
        this.numeroStanze = numeroStanze;
    }

    public void setPiano(Integer piano) {
        this.piano = piano;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }
}
