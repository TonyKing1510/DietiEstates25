package it.unina.webtech.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.Notifica;


public class NotDaoImpl implements NotDao {
    @Override
    public List<Notifica> getNot() {
        List<Notifica> notifications = new ArrayList<>();
        String query = "SELECT titolo,contenuto FROM notifica";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String titolo = resultSet.getString("titolo");
                String contenuto = resultSet.getString("contenuto");
                Notifica notification = new Notifica(titolo, contenuto);
                notifications.add(notification);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
