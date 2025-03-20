package it.unina.webtech.dto.response;
import it.unina.webtech.model.Arredamento;
import it.unina.webtech.model.ClasseEnergetica;
import it.unina.webtech.model.TipoImmobile;
import it.unina.webtech.model.TipoVendita;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.List;

@Setter
@Getter

public class ImmobileResponseRicercaDTO {

    private int idImmobile;
    private TipoImmobile tipoimmobile;
    private TipoVendita tipovendita;
    private String descrizione;
    private String via;
    private int numeroStanze;
    private int piano;
    private ClasseEnergetica classeEnergetica;

    private int superficie;
    private Arredamento arredamento;
    private String prezzo;
    private String speseCondominiali;
    private int numeroBagni;
    private int numeroCucine;
    private int numeroSoggiorni;
    private String cfAgente;
    private double latitudine;
    private double longitudine;
    private String numeroCivico;
    private String comune;
    private String citta;
    private String titolo;
    private List<String> uriFotoImmobile;
    private String nomeAgente;
    private String cognomeAgente;


    public String formattaStringa(String input){
        double number = Double.parseDouble(input);
        long roundedNumber = Math.round(number); // Arrotonda se necessario
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(roundedNumber);

    }

    private ImmobileResponseRicercaDTO(Builder builder) {
        this.idImmobile = builder.idImmobile;
        this.tipoimmobile = builder.tipoimmobile;
        this.tipovendita = builder.tipovendita;
        this.descrizione = builder.descrizione;
        this.via = builder.via;
        this.numeroStanze = builder.numeroStanze;
        this.piano = builder.piano;
        this.classeEnergetica = builder.classeEnergetica;
        this.superficie = builder.superficie;
        this.arredamento = builder.arredamento;
        this.prezzo = builder.prezzo;
        this.speseCondominiali = builder.speseCondominiali;
        this.numeroBagni = builder.numeroBagni;
        this.numeroCucine = builder.numeroCucine;
        this.numeroSoggiorni = builder.numeroSoggiorni;
        this.cfAgente = builder.cfAgente;
        this.latitudine = builder.latitudine;
        this.longitudine = builder.longitudine;
        this.numeroCivico = builder.numeroCivico;
        this.comune = builder.comune;
        this.citta = builder.citta;
        this.titolo = builder.titolo;
        this.nomeAgente= builder.nomeAgente;
        this.cognomeAgente= builder.cognomeAgente;
    }

    public static class Builder {
        private int idImmobile;
        private TipoImmobile tipoimmobile;
        private TipoVendita tipovendita;
        private String descrizione;
        private String via;
        private int numeroStanze;
        private int piano;
        private ClasseEnergetica classeEnergetica;
        private int superficie;
        private Arredamento arredamento;
        private String prezzo;
        private String speseCondominiali;
        private int numeroBagni;
        private int numeroCucine;
        private int numeroSoggiorni;
        private String cfAgente;
        private double latitudine;
        private double longitudine;
        private String numeroCivico;
        private String comune;
        private String citta;
        private String titolo;
        private String cognomeAgente;
        private String nomeAgente;


        public Builder idImmobile(int idImmobile) {
            this.idImmobile = idImmobile;
            return this;
        }


        public Builder nomeAgente(String nomeAgente) {
            this.nomeAgente = nomeAgente;
            return this;
        }

        public Builder cognomeAgente(String cognomeAgente) {
            this.cognomeAgente = cognomeAgente;
            return this;
        }

        public Builder tipoimmobile(TipoImmobile tipoimmobile) {
            this.tipoimmobile = tipoimmobile;
            return this;
        }

        public Builder tipovendita(TipoVendita tipovendita) {
            this.tipovendita = tipovendita;
            return this;
        }

        public Builder descrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        public Builder via(String via) {
            this.via = via;
            return this;
        }

        public Builder numeroStanze(int numeroStanze) {
            this.numeroStanze = numeroStanze;
            return this;
        }

        public Builder piano(int piano) {
            this.piano = piano;
            return this;
        }

        public Builder classeEnergetica(ClasseEnergetica classeEnergetica) {
            this.classeEnergetica = classeEnergetica;
            return this;
        }

        public Builder superficie(int superficie) {
            this.superficie = superficie;
            return this;
        }

        public Builder arredamento(Arredamento arredamento) {
            this.arredamento = arredamento;
            return this;
        }

        public Builder prezzo(String prezzo) {
            this.prezzo = prezzo;
            return this;
        }

        public Builder speseCondominiali(String speseCondominiali) {
            this.speseCondominiali = speseCondominiali;
            return this;
        }

        public Builder numeroBagni(int numeroBagni) {
            this.numeroBagni = numeroBagni;
            return this;
        }

        public Builder numeroCucine(int numeroCucine) {
            this.numeroCucine = numeroCucine;
            return this;
        }

        public Builder numeroSoggiorni(int numeroSoggiorni) {
            this.numeroSoggiorni = numeroSoggiorni;
            return this;
        }

        public Builder cfAgente(String cfAgente) {
            this.cfAgente = cfAgente;
            return this;
        }

        public Builder latitudine(double latitudine) {
            this.latitudine = latitudine;
            return this;
        }

        public Builder longitudine(double longitudine) {
            this.longitudine = longitudine;
            return this;
        }

        public Builder numeroCivico(String numeroCivico) {
            this.numeroCivico = numeroCivico;
            return this;
        }

        public Builder comune(String comune) {
            this.comune = comune;
            return this;
        }

        public Builder citta(String citta) {
            this.citta = citta;
            return this;
        }

        public Builder titolo(String titolo) {
            this.titolo = titolo;
            return this;
        }

        public ImmobileResponseRicercaDTO build() {
            return new ImmobileResponseRicercaDTO(this);
        }
    }


}

