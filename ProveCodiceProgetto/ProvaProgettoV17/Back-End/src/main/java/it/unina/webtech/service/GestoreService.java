package it.unina.webtech.service;
import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GestoreService {
    private static GestoreDao dao;

    public GestoreService(){}

    public static List<String> getAllCfService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllCF();
    }

    public static List<String> getAllUserNameService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllUsername();
    }

    public static List<String> getAllEmailService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllEmail();
    }

    public static Map<String,Object> getGestoreByUsername(String username) throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getGestoreByUsername(username);
    }
}
