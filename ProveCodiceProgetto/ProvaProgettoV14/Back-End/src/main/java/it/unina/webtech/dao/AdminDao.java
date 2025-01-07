package it.unina.webtech.dao;

import it.unina.webtech.model.Admin;

import java.sql.SQLException;

public interface AdminDao {
    public boolean addAdmin(Admin admin) throws SQLException;
}
