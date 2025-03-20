package it.unina.webtech.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PosizioneGeograficaImmobile {
    private String via;
    private String comune;
    private String numeroCivico;
    private String citta;
    private int longitudine;
    private int latitudine;


}
