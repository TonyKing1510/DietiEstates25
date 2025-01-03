package it.unina.webtech.dao;


import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.AccountSemplice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(AccountSemplice user) {
        String sql = "INSERT INTO cliente (cf,email,password) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,"SVNGLN020002");
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore durante il salvataggio dell'utente: " + e.getMessage());
        }
    }
}
