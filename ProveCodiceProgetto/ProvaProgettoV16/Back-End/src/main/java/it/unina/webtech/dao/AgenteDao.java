package it.unina.webtech.dao;

import it.unina.webtech.model.Agente;

import java.sql.SQLException;

public interface AgenteDao {

    public boolean addAgente(Agente agente) throws SQLException;

    public Agente getAgente(String cf);

    public String getGestoreRiferimento(String cf);

}
