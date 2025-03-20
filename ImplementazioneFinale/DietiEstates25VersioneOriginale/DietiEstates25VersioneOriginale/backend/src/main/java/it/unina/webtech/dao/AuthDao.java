package it.unina.webtech.dao;

import it.unina.webtech.dto.response.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public interface AuthDao {
    boolean checkIfExists(AccountSemplice account) throws SQLException;

    GestoreAgenziaImmobiliareDTO checkGestoreExists(GestoreAgenziaImmobiliare g, String psw) throws SQLException;


}
