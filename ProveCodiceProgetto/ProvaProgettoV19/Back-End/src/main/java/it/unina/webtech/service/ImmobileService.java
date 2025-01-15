package it.unina.webtech.service;

import it.unina.webtech.dao.ImmobileDao;
import it.unina.webtech.dao.ImmobileDaoImpl;
import it.unina.webtech.dto.ImmobileDTO;
import it.unina.webtech.model.Immobile;

import java.sql.SQLException;

public class ImmobileService {
    public static ImmobileDTO addImmobile(Immobile immobile){
        try{
            ImmobileDao immobileDao = new ImmobileDaoImpl();
            return immobileDao.addImmobile(immobile);
        } catch (SQLException e) {
            e.printStackTrace();
           return new ImmobileDTO(false,true,true);
        }
    }

}

