package it.unina.webtech.dao;

import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public interface AccountDao {
    public boolean checkIfExists(AccountSemplice account) throws SQLException;

    public GestoreAgenziaImmobiliareDTO checkGestoreExists(GestoreAgenziaImmobiliare g, String psw) throws SQLException;
}
