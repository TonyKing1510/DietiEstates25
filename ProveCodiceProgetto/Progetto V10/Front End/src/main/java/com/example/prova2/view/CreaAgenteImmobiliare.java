package com.example.prova2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreaAgenteImmobiliare {

    private CreaAgenteImmobiliare(){}

    public static void initializePageCreaAgente(Window window) throws IOException {
        System.out.println("mammt");
        FXMLLoader loader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/creaAgenteImmobiliare.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
    }
}
