package com.example.prova2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginAgenteImmobiliare {

    // Costruttore per inizializzare lo stage
    public LoginAgenteImmobiliare(Window window) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomePageLogin.class.getResource("/com/example/prova2/ProvaLoginAgente.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
    }
}