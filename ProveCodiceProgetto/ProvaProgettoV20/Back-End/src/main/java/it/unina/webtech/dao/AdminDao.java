package it.unina.webtech.dao;

import it.unina.webtech.model.Admin;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public interface AdminDao {
    public boolean addAdmin(Admin admin) throws SQLException;

    public boolean updatePassword(GestoreAgenziaImmobiliare gestore) throws SQLException;
}
