package it.unina.webtech.service;

import it.unina.webtech.dao.AdminDao;
import it.unina.webtech.dao.AdminDaoImpl;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public class UpdatePasswordService {

    // Costruttore privato per impedire l'istanziazione
    private UpdatePasswordService() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean updatePassword(GestoreAgenziaImmobiliare gestore) {
        try{
            AdminDao dao = new AdminDaoImpl();
            gestore.getAccountGestore().setPassword(SecurityPasswordService.hashPassword(gestore.getAccountGestore().getPassword()));
            return dao.updatePassword(gestore);
        } catch (SQLException e) {
            return false;
        }
    }
}
