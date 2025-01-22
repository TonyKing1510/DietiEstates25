package it.unina.webtech.service;
import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.dto.AgenteDTO;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;

import java.sql.SQLException;

public class LoginUtenteService {

    public static AgenteDTO exists(AccountSemplice credenziali, String tipoUtente) {
        try {
            UserDao dao = new UserDaoImpl();
            switch (tipoUtente) {
                case "agente":
                    return dao.findAgenteByUsernameAndPassword(credenziali.getEmail(), credenziali.getPassword());
                case "cliente":
                    return dao.findClienteByUsernameAndPassword(credenziali.getEmail(), credenziali.getPassword());
                default: return new AgenteDTO(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new AgenteDTO(true);
        }
    }

    public static GestoreAgenziaImmobiliareDTO existsGestore(AccountSemplice credenziali) {
        try {
            UserDao dao = new UserDaoImpl();
            return dao.findGestoreByUsernameAndPassword(credenziali.getEmail(), credenziali.getPassword());
        } catch (SQLException e) {
            return null;
        }
    }
}
