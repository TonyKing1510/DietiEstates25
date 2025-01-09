package it.unina.webtech.service;

import it.unina.webtech.dao.NotDao;
import it.unina.webtech.dao.NotDaoImpl;

public class NotificationService {

    // Costruttore privato per impedire l'istanziazione
    private NotificationService() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static void setNotificationAccepted(int idNotifica){
        NotDao notDao = new NotDaoImpl();
        notDao.setNotificaAccepted(idNotifica);
    }
    public static void setNotificationRejected(int idNotifica){
        NotDao notDao = new NotDaoImpl();
        notDao.setNotificaRejected(idNotifica);
    }
}
