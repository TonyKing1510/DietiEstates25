package com.example.prova2.view;

import com.example.prova2.controller.SchermataAnnuncioController;
import com.example.prova2.dto.ImmobileResponseRicercaDTO;
import com.example.prova2.model.Utente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class SchermataAnnuncio {

    public static void initializeSchermataAnnuncio(Pane pane, ImmobileResponseRicercaDTO annuncio, Utente utente , BigDecimal prezzoMin, BigDecimal prezzoMax) throws IOException {
        FXMLLoader loader = new FXMLLoader(SchermataAnnuncio.class.getResource("/com/example/prova2/views/shared/schermataAnnuncio.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 900);
        SchermataAnnuncioController controller = loader.getController();
        controller.setDatiRicerca(annuncio,prezzoMin,prezzoMax);
        controller.setUtenteCheCerca(utente);
        controller.loadAnnuncio(annuncio);

        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
