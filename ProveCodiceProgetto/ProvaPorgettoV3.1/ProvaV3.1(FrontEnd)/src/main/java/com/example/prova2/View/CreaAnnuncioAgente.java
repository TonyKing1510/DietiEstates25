package com.example.prova2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreaAnnuncioAgente {
    // Costruttore per inizializzare lo stage
    public CreaAnnuncioAgente(Window window) throws IOException , InterruptedException {
        FXMLLoader loader = new FXMLLoader(HomePageLogin.class.getResource("/com/example/prova2/SchermataCreaAnnuncio.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
    }
}
