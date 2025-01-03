package it.unina.webtech.dao;

import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.Admin;

import java.sql.SQLException;

public interface AccountDao {
    public boolean checkIfExists(AccountSemplice account) throws SQLException;

    public boolean checkAdminExists(Admin admin) throws SQLException;
}
