package it.unina.webtech.model;

public class PosizioneGeograficaImmobile {
    private String via;
    private String comune;
    private String numeroCivico;
    private String città;
    private int longitudine;
    private int latitudine;


    public String getComune() {
        return comune;
    }

    public String getCittà() {
        return città;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public String getVia() {
        return via;
    }

    public int getLatitudine() {
        return latitudine;
    }

    public int getLongitudine() {
        return longitudine;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setLatitudine(int latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(int longitudine) {
        this.longitudine = longitudine;
    }
}
