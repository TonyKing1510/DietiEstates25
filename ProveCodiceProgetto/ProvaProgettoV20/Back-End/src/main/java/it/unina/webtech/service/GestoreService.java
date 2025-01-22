package it.unina.webtech.service;
import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDatiDTO;

import java.sql.SQLException;
import java.util.List;

public class GestoreService {

    private GestoreService(){}

    public static List<String> getAllCfService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllCF();
    }


    public static List<String> getAllEmailService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllEmail();
    }

    public static GestoreAgenziaImmobiliareDatiDTO getGestoreByUsername(String email) throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getGestoreByEmail(email);
    }
}
