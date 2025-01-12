package it.unina.webtech.service;
import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.SQLException;

public class LoginUtenteService {

    public static boolean exists(AccountSemplice credenziali,String tipoUtente) {
        try {
            UserDao dao = new UserDaoImpl();
            switch (tipoUtente) {
                case "agente":
                    return dao.findAgenteByUsernameAndPassword(credenziali.getUsername(), credenziali.getPassword());
                case "cliente":
                    return dao.findClienteByUsernameAndPassword(credenziali.getUsername(), credenziali.getPassword());
                default: return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static GestoreAgenziaImmobiliareDTO existsGestore(AccountSemplice credenziali) {
        try {
            UserDao dao = new UserDaoImpl();
            return dao.findGestoreByUsernameAndPassword(credenziali.getUsername(), credenziali.getPassword());
        } catch (SQLException e) {
            return null;
        }
    }
}
