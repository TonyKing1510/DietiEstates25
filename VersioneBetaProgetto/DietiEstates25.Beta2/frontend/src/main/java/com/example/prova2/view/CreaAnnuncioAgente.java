package com.example.prova2.view;

import com.example.prova2.controller.CreaAnnuncioController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreaAnnuncioAgente {

    private CreaAnnuncioAgente(){}


    public static void initializePageCreaAnnuncio(Stage stagePrecedente, String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/views/agente/creaAnnuncio.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        CreaAnnuncioController controller = loader.getController();
        controller.setCf(s);

        Stage nuovoStage = new Stage();
        controller.stagePrecedente = stagePrecedente;
        controller.stageOra = nuovoStage;

        nuovoStage.setScene(scene);
        nuovoStage.show();
    }

}
