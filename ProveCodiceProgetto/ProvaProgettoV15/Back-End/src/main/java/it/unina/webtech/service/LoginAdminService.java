package it.unina.webtech.service;
import it.unina.webtech.dao.AccountDaoImpl;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public class LoginAdminService {
    public static boolean loginAdmin(GestoreAgenziaImmobiliare g) throws SQLException {
        try {
            AccountDaoImpl dao = new AccountDaoImpl();
            return dao.checkAdminExists(g,g.getAccountGestore().getPassword());
        }catch(SQLException e){
            return false;
        }
    }
}
