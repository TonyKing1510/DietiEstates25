package it.unina.webtech.service;

import it.unina.webtech.dao.NotDao;
import it.unina.webtech.dao.NotDaoImpl;
import it.unina.webtech.model.Notifica;

import java.util.List;

public class GetNotService {
    public static List<Notifica> getNotificationOfAdminService(String partitaIva) {
            NotDaoImpl dao = new NotDaoImpl();
            return dao.getNotofAdmin(partitaIva);
    }
}
