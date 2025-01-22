package it.unina.webtech.dao;


import it.unina.webtech.dto.AgenteDTO;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public interface UserDao {
    void save(AccountSemplice user);

    public AgenteDTO findAgenteByUsernameAndPassword(String username, String password) throws SQLException;

    public AgenteDTO findClienteByUsernameAndPassword(String username, String password) throws SQLException;

    public GestoreAgenziaImmobiliareDTO findGestoreByUsernameAndPassword(String username, String password) throws SQLException;

}
