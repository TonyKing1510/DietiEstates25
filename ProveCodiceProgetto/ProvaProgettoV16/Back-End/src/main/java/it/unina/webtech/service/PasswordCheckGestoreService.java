package it.unina.webtech.service;

import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public class PasswordCheckGestoreService {
    public static boolean checkPassword(GestoreAgenziaImmobiliare g) {
        try {
            GestoreDao dao = new GestoreDaoImpl();
            String passwordhash=dao.getSecurityPassword(g.getCf());
            if(passwordhash == null){
                return false;
            }
            return SecurityPasswordService.checkPassword(g.getAccountGestore().getPassword(),passwordhash);
        } catch (SQLException e) {
            return false;
        }

    }
}
