package it.unina.webtech.dao;

import it.unina.webtech.model.AccountSemplice;

import java.sql.SQLException;

public interface AccountDao {
    public boolean checkIfExists(AccountSemplice account) throws SQLException;
}
