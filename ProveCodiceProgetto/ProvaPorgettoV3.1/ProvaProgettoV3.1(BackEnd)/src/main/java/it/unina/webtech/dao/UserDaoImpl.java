package it.unina.webtech.dao;


import it.unina.webtech.model.User;
import it.unina.webtech.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (mail, password) VALUES (?, ?)";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getMail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (
                SQLException e) {
            System.err.println("Errore durante il salvataggio dell'utente: " + e.getMessage());
        }
    }
}
