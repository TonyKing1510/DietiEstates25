package it.unina.webtech.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.dto.NotificaDTO;
import it.unina.webtech.model.Notifica;


public class NotDaoImpl implements NotDao {

    private Connection connection;

    public NotDaoImpl(){
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Notifica> getNotofAdmin(String cf) {
        List<Notifica> notifications = new ArrayList<>();
        String query = "SELECT titolo,contenuto,id_notifica FROM notifica n where n.gestoreNotificato = ? and accettata = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cf);
            ResultSet resultSet = preparedStatement.executeQuery();
            aggiungiNotifiche(notifications, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public List<Notifica> getNotificaAgente(String cf) {
        List<Notifica> notifications = new ArrayList<>();
        String query = "SELECT titolo,contenuto,id_notifica FROM notifica n where n.agenteNotificato = ? and accettata = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cf);
            ResultSet resultSet = preparedStatement.executeQuery();
            aggiungiNotifiche(notifications, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    private void aggiungiNotifiche(List<Notifica> notifications, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String titolo = resultSet.getString("titolo");
            String contenuto = resultSet.getString("contenuto");
            int idNotifica = resultSet.getInt("id_notifica");
            Notifica notification = new Notifica(titolo, contenuto,idNotifica);
            notifications.add(notification);
        }
    }


    @Override
    public NotificaDTO setNotificaAccepted(int idNotifica) {
        String sql = "UPDATE notifica SET accettata = true WHERE id_notifica = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idNotifica);
            int nRows=preparedStatement.executeUpdate();
            if(nRows > 0){
                NotificaDTO dto = new NotificaDTO();
                dto.setNumeroNotificheAccettate(nRows);
                return dto;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
