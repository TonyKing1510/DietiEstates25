package it.unina.webtech.dao;
import it.unina.webtech.dto.response.NotificaDTO;
import it.unina.webtech.model.Notifica;

import java.util.List;

public interface NotDao {

    List<Notifica> getNotofAdmin(String partitaIva);

    NotificaDTO setNotificaAccepted(int idNotifica);

    NotificaDTO setNotificaRejected(int idNotifica);

    List<Notifica> getNotificaAgente(String cf);

    boolean annullaInvioNotifica(int id);

    List<Notifica> getNotificaCliente(String email);
}
