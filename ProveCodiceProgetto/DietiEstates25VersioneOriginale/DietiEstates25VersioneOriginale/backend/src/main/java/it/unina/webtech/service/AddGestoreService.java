package it.unina.webtech.service;

import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.dto.request.AddGestoreRequestDTO;
import it.unina.webtech.dto.response.GestoreDTO;
import java.sql.SQLException;

public class AddGestoreService {

    private AddGestoreService(){}

    public static GestoreDTO addGestore(AddGestoreRequestDTO gestore) {
        try {
            GestoreDao dao = new GestoreDaoImpl();
            gestore.setPassword(SecurityPasswordService.hashPassword(gestore.getPassword()));
            return dao.addGestore(gestore);
        } catch (SQLException e) {
            return new GestoreDTO(false,false,true);
        }
    }
}
