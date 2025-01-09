package com.example.prova2.view;

import com.example.prova2.controller.CreaAgenteImmobiliareController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreaAgenteImmobiliare {

    private CreaAgenteImmobiliare(){}

    public static void initializePageCreaAgente(Window window) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/creaAgenteImmobiliare.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        CreaAgenteImmobiliareController controller = loader.getController();
        controller.initialize();
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
    }
}
