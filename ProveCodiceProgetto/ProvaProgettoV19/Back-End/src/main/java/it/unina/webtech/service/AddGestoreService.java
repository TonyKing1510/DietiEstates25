package it.unina.webtech.service;

import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import java.sql.SQLException;

public class AddGestoreService {
    public static boolean addGestore(GestoreAgenziaImmobiliare gestore) {
        try {
            GestoreDao dao = new GestoreDaoImpl();
            gestore.getAccountGestore().setPassword(SecurityPasswordService.hashPassword(gestore.getAccountGestore().getPassword()));
            return dao.addGestore(gestore);
        } catch (SQLException e) {
            return false;
        }
    }
}
