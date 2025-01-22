package it.unina.webtech.model;

import java.util.ArrayList;

public class Agente extends Utente {
    private String partitaIva;

    private ArrayList<Recensione> recensioniAgente;

    private ArrayList<Notifica> notificheAgente;

    private ArrayList<Immobile> immobiliCaricati;

    private ArrayList<Visita> visiteAgente;

    private AccountAziendale accountAgente;

    private GestoreAgenziaImmobiliare gestoreRiferimento;

    public Agente() {}

    Agente(String nome, String cognome, String cf, String telefono, String indirizzo) {
        super(nome, cognome, cf, telefono, indirizzo);
    }

    public GestoreAgenziaImmobiliare getGestoreRiferimento() {
        return gestoreRiferimento;
    }

    public void setGestoreRiferimento(GestoreAgenziaImmobiliare gestoreRiferimento) {
        this.gestoreRiferimento = gestoreRiferimento;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    @Override
    public String getCf() {
        return super.getCf();
    }

    @Override
    public void setCf(String cf) {
        super.setCf(cf);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setCognome(String cognome) {
        super.setCognome(cognome);
    }

    @Override
    public String getCognome() {
        return super.getCognome();
    }

    @Override
    public String getNumeroCivico() {
        return super.getNumeroCivico();
    }

    @Override
    public String getTelefono() {
        return super.getTelefono();
    }

    @Override
    public String getVia() {
        return super.getVia();
    }

    public AccountAziendale getAccountAgente() {
        return accountAgente;
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public void setNumeroCivico(String numeroCivico) {
        super.setNumeroCivico(numeroCivico);
    }

    @Override
    public void setTelefono(String telefono) {
        super.setTelefono(telefono);
    }

    @Override
    public void setVia(String via) {
        super.setVia(via);
    }

    public void setAccountAgente(AccountAziendale accountAgente) {
        this.accountAgente = accountAgente;
    }


}
