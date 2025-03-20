package it.unina.webtech.model;

import lombok.Getter;

@Getter
public class Agenzia {
    private String nomeAgenzia;

    public Agenzia(String societa) {
        this.nomeAgenzia = societa;
    }

}
