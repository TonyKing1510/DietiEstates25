package it.unina.webtech.service;

import it.unina.webtech.dao.NotDao;
import it.unina.webtech.model.Notifica;

import java.util.List;

public class GetNotService {
    private final NotDao notDao;

    // Costruttore che riceve il DAO
    public GetNotService(NotDao notDao) {
        this.notDao = notDao;
    }

    // Metodo per ottenere tutte le notifiche
    public List<Notifica> getNot() {
        return notDao.getNot(); // Chiama il metodo getNot() del DAO
    }
}
