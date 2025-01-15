package it.unina.webtech.dao;


import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public interface UserDao {
    void save(AccountSemplice user);

    public boolean findAgenteByUsernameAndPassword(String username, String password) throws SQLException;

    public boolean findClienteByUsernameAndPassword(String username, String password) throws SQLException;

    public GestoreAgenziaImmobiliareDTO findGestoreByUsernameAndPassword(String username, String password) throws SQLException;

}
