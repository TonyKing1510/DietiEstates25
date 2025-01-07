package it.unina.webtech.Test;

import org.junit.jupiter.api.*;
import java.sql.*;


public class PostgresTest {
    private static Connection con;
    private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Napoli999!";

    @BeforeAll
    public static void databaseConnectionTest() throws Exception{
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    @Test
    public void testInsertData() throws Exception{
        con.createStatement().execute("INSERT INTO \"TestDiProva\".users (mail,password) VALUES ('Tony@gmail.com','Napoli3332')");

        ResultSet result = con.createStatement().executeQuery("SELECT * FROM \"TestDiProva\".users");
        Assertions.assertTrue(result.next());
        Assertions.assertEquals("Tony@gmail.com", result.getString("mail"));
        Assertions.assertEquals("Napoli3332", result.getString("password"));

    }

    @Test
    public void testInvalidLogin() throws Exception {
        ResultSet result = con.createStatement().executeQuery("SELECT * FROM \"TestDiProva\".users WHERE mail='wrongUser@gmail.com' AND password='WrongPassword'");
        Assertions.assertFalse(result.next());
    }

    @Test
    public void testNullFields() throws Exception {
        Assertions.assertThrows(SQLException.class, () -> {
            con.createStatement().execute("INSERT INTO \"TestDiProva\".users (mail, password) VALUES (NULL, 'password')");
        });
    }

    @AfterAll
    public static void closeConnectionAndDeleteData() throws Exception{
        con.createStatement().execute("DELETE FROM \"TestDiProva\".users");
        con.close();
    }
}
