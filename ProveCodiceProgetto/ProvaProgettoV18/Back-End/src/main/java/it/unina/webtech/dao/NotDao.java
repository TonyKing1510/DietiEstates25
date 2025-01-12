package it.unina.webtech.dao;

import it.unina.webtech.dto.NotificaDTO;
import it.unina.webtech.model.Notifica;

import java.util.List;

public interface NotDao {
    List<Notifica> getNot();

    List<Notifica> getNotofAdmin(String partitaIva);

    public NotificaDTO setNotificaAccepted(int idNotifica);

}
