package it.unina.webtech.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://ep-empty-waterfall-a5teimlb.us-east-2.aws.neon.tech/DietiEstates?user=neondb_owner&password=J7DVYAX3kjqB&sslmode=require";


    private static Connection connection = null;

    // Metodo per ottenere la connessione
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
            System.out.println(connection);
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
