package it.unina.webtech.dao;

import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.Admin;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public interface AccountDao {
    public boolean checkIfExists(AccountSemplice account) throws SQLException;

    public boolean checkAdminExists(GestoreAgenziaImmobiliare g,String psw) throws SQLException;
}
