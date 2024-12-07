package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.Image;
import it.unina.webtech.model.Notification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl implements ImageDao {
    @Override
    public void addImage(File file) {
        String query = "INSERT INTO immagini (nome_file, dati) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, file.getName());
            preparedStatement.setBytes(2, fileToBytes(file));

            preparedStatement.executeUpdate();
            System.out.println("Immagine aggiunta con successo.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<>();
        String query = "SELECT * FROM immagini";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomeFile = resultSet.getString("nome_file");
                byte[] dati = resultSet.getBytes("dati");

                Image image = new Image(id, nomeFile, dati);
                images.add(image);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }

    private byte[] fileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }
}
