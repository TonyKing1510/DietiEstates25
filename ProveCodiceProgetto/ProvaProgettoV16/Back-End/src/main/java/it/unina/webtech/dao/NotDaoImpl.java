package it.unina.webtech.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.unina.webtech.db.DatabaseConnection;
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
    public List<Notifica> getNot() {
        List<Notifica> notifications = new ArrayList<>();
        String query = "SELECT titolo,contenuto,id_notifica FROM notifica n join gestoreagenziaimmobiliare g on n.gestorenotificato=g.cf where g.partitaIva = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String titolo = resultSet.getString("titolo");
                String contenuto = resultSet.getString("contenuto");
                int id_notifica = resultSet.getInt("id_notifica");
                Notifica notification = new Notifica(titolo, contenuto,id_notifica);
                notifications.add(notification);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public List<Notifica> getNotofAdmin(String cf) {
        List<Notifica> notifications = new ArrayList<>();
        String query = "SELECT titolo,contenuto,id_notifica FROM notifica n join gestoreagenziaimmobiliare g on n.gestorenotificato=g.cf where g.cf = ? and accettata = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cf);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String titolo = resultSet.getString("titolo");
                String contenuto = resultSet.getString("contenuto");
                int id_notifica = resultSet.getInt("id_notifica");
                Notifica notification = new Notifica(titolo, contenuto,id_notifica);
                notifications.add(notification);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }


    @Override
    public void setNotificaAccepted(int idNotifica) {
        String sql = "UPDATE notifica SET accettata = true WHERE id_notifica = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Usa setInt per passare l'id_notifica come intero
            preparedStatement.setInt(1, idNotifica);

            // Esegui l'update
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setNotificaRejected(int idNotifica) {
        String sql = "DELETE FROM notifica WHERE id_notifica = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Imposta l'id_notifica come parametro nella query
            preparedStatement.setInt(1, idNotifica);

            // Esegui il delete
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
