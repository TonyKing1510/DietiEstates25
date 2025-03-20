package it.unina.webtech.dto.response;

import it.unina.webtech.model.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class InformazioniVisitaDTO {
    private String emailClientePrenotatoVisita;
    private TipoImmobile tipoImmobile;
    private String viaImmobile;
    private String comune;
    private String numeroCivico;
    private int idImmobile;
    private LocalDate dataVisita;
    private LocalTime oraInizioVisita;
    private LocalTime oraFineVisita;
    private String agente;
    private String fotoProfiloAgente;

    public InformazioniVisitaDTO(Builder builder){
        this.emailClientePrenotatoVisita = builder.emailClientePrenotatoVisita;
        this.tipoImmobile = builder.tipoImmobile;
        this.viaImmobile = builder.viaImmobile;
        this.comune = builder.comune;
        this.numeroCivico = builder.numeroCivico;
        this.idImmobile = builder.idImmobile;
        this.dataVisita = builder.dataVisita;
        this.oraInizioVisita = builder.oraInizioVisita;
        this.oraFineVisita=builder.oraFineVisita;
        this.agente=builder.agente;
        this.fotoProfiloAgente=builder.fotoProfiloAgente;
    }

    public static class Builder {
        private String emailClientePrenotatoVisita;
        private TipoImmobile tipoImmobile;
        private String viaImmobile;
        private String comune;
        private String numeroCivico;
        private int idImmobile;
        private LocalDate dataVisita;
        private LocalTime oraInizioVisita;
        private LocalTime oraFineVisita;
        private String agente;
        private String fotoProfiloAgente;

        public Builder fotoProfiloAgente(String fotoProfiloAgente) {
            this.fotoProfiloAgente = fotoProfiloAgente;
            return this;
        }

        public Builder agente(String agente) {
            this.agente = agente;
            return this;
        }

        public Builder emailClientePrenotatoVisita(String emailClientePrenotatoVisita) {
            this.emailClientePrenotatoVisita = emailClientePrenotatoVisita;
            return this;
        }
        public Builder tipoImmobile(TipoImmobile tipoImmobile) {
            this.tipoImmobile = tipoImmobile;
            return this;
        }
        public Builder viaImmobile(String viaImmobile) {
            this.viaImmobile = viaImmobile;
            return this;
        }
        public Builder comune(String comune) {
            this.comune = comune;
            return this;
        }
        public Builder numeroCivico(String numeroCivico) {
            this.numeroCivico = numeroCivico;
            return this;
        }
        public Builder idImmobile(int idImmobile) {
            this.idImmobile = idImmobile;
            return this;
        }
        public Builder dataVisita(LocalDate dataVisita) {
            this.dataVisita = dataVisita;
            return this;
        }
        public Builder oraVisita(LocalTime oraVisita) {
            this.oraInizioVisita = oraVisita;
            return this;
        }
        public Builder oraFineVisita(LocalTime oraFineVisita){
            this.oraFineVisita=oraFineVisita;
            return this;
        }
        public InformazioniVisitaDTO build() {
            return new InformazioniVisitaDTO(this);
        }
    }

}
