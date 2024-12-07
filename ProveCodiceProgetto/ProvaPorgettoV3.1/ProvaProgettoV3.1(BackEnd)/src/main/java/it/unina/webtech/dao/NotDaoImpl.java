package it.unina.webtech.dao;

import it.unina.webtech.model.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.unina.webtech.db.DatabaseConnection;



public class NotDaoImpl implements NotDao {
    @Override
    public List<Notification> getNot() {
        List<Notification> notifications = new ArrayList<>();
        String query = "SELECT idn, title, content FROM notifications";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Elaborazione del risultato
            while (resultSet.next()) {
                int id = resultSet.getInt("idn");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                Notification notification = new Notification(id, title, content);
                notifications.add(notification);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
}
