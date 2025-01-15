package it.unina.webtech.service;

import it.unina.webtech.dao.NotDao;
import it.unina.webtech.dao.NotDaoImpl;
import it.unina.webtech.dto.NotificaDTO;
import it.unina.webtech.model.Notifica;

import java.util.List;

public class NotificationsService {
    public static List<Notifica> getNotificationOfAdminService(String cf) {
            NotDaoImpl dao = new NotDaoImpl();
            return dao.getNotofAdmin(cf);
    }

    public static NotificaDTO setNotificationAccepted(int idNotifica){
        NotDao notDao = new NotDaoImpl();
        return notDao.setNotificaAccepted(idNotifica);
    }
}
