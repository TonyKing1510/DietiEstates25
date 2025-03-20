package it.unina.webtech.model;

import lombok.Getter;

@Getter
public class Admin extends GestoreAgenziaImmobiliare {
    private final AccountAmministratore accountAmministratore = new AccountAmministratore();

}
