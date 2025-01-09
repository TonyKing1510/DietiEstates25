package it.unina.webtech.service;

import it.unina.webtech.dao.NotDaoImpl;
import it.unina.webtech.model.Notifica;

import java.util.List;

public class GetNotService {

    // Costruttore privato per impedire l'istanziazione
    private GetNotService() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static List<Notifica> getNotificationOfAdminService(String partitaIva) {
            NotDaoImpl dao = new NotDaoImpl();
            return dao.getNotofAdmin(partitaIva);
    }
}
