package it.unina.webtech.service;
import it.unina.webtech.dao.AccountDaoImpl;
import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public class PrimoLoginAdminService {
    public static GestoreAgenziaImmobiliareDTO loginAdmin(GestoreAgenziaImmobiliare g) throws SQLException {
        try {
            AccountDaoImpl dao = new AccountDaoImpl();
            return dao.checkAdminExists(g,g.getAccountGestore().getPassword());
        }catch(SQLException e){
            return null;
        }
    }

    public static boolean updatePrimoAdmin(GestoreAgenziaImmobiliare g){
        try {
            GestoreDao dao = new GestoreDaoImpl();
            return dao.updatePrimoAccesso(g.getCf());
        }catch(SQLException e){
            return false;
        }
    }
}
