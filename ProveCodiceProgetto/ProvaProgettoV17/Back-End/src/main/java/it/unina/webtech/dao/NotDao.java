package it.unina.webtech.dao;

import it.unina.webtech.model.Notifica;

import java.util.List;

public interface NotDao {
    List<Notifica> getNot();

    List<Notifica> getNotofAdmin(String partitaIva);

    public void setNotificaAccepted(int idNotifica);
    public void setNotificaRejected(int idNotifica);
}
