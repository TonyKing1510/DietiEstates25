package it.unina.webtech.model;

public class DettagliVenditaImmobile {
    private Float prezzo;
    private tipovendita tipoVendita;
    private Float speseCondominiali;
    private Agente agenteProprietario;
    private it.unina.webtech.model.arredamento arredamento;
    private tipoimmobile tipoImmobile;



    public void setArredamento(it.unina.webtech.model.arredamento arredamento) {
        this.arredamento = arredamento;
    }

    public void setTipoImmobile(tipoimmobile tipoImmobile) {
        this.tipoImmobile = tipoImmobile;
    }

    public tipoimmobile getTipoImmobile() {
        return tipoImmobile;
    }

    public it.unina.webtech.model.arredamento getArredamento() {
        return arredamento;
    }

    public tipovendita getTipoVendita() {
        return tipoVendita;
    }

    public Agente getAgenteProprietario() {
        return agenteProprietario;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public Float getSpeseCondominiali() {
        return speseCondominiali;
    }

    public void setAgenteProprietario(Agente agenteProprietario) {
        this.agenteProprietario = agenteProprietario;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setSpeseCondominiali(float speseCondominiali) {
        this.speseCondominiali = speseCondominiali;
    }

    public void setTipoVendita(tipovendita tipoVendita) {
        this.tipoVendita = tipoVendita;
    }
}
