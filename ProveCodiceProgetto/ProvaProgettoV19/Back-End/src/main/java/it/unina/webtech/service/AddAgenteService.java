package it.unina.webtech.service;

import it.unina.webtech.dao.AgenteDao;
import it.unina.webtech.dao.AgenteDaoImpl;
import it.unina.webtech.model.Agente;

import java.sql.SQLException;

public class AddAgenteService {
    public boolean addAgenteService(Agente agente){
        try {
            AgenteDao dao = new AgenteDaoImpl();
            agente.getAccountAgente().setPassword(SecurityPasswordService.hashPassword(agente.getAccountAgente().getPassword()));
            return dao.addAgente(agente);
        }
        catch (SQLException e) {
            return false;
        }
    }

}
