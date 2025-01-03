package it.unina.webtech.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/provaEstates";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Napoli999!";

    private static Connection connection = null;

    // Metodo per ottenere la connessione
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }


    // Metodo per chiudere la connessione
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
