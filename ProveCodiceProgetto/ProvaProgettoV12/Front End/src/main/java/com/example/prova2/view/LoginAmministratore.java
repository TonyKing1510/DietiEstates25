package com.example.prova2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginAmministratore {
    private LoginAmministratore(){}

    public static void initializePageLoginAmministratore(Window window) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/loginAdmin.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.setFullScreen(true); // Mantieni schermo intero anche nella scena di login
        stage.setResizable(true); // Consenti ridimensionamento
        stage.show();
    }
}
