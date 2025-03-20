package com.example.prova2.controller.prenotaVisita;

import com.example.prova2.controller.SchermataRicercaController;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.Immobile;
import com.example.prova2.view.EffettuaPrenotazione;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class AlertVisitaController {
    @FXML
    public Label nomeAgente;
    @FXML
    public Label richiediVisitaLabel;

    private Immobile immobile;

    public void init(Immobile immobile){
        this.immobile = immobile;
        nomeAgente.setText(immobile.getNomeAgente()+" "+immobile.getCognomeAgente());
    }

    public void richiediVisita() {
        try {
            EffettuaPrenotazione.initPage((Stage) richiediVisitaLabel.getScene().getWindow(),
                    SchermataRicercaController.getUtenteCheCerca().getAccountAgente().getEmail(), immobile);
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreInterno();
        }
    }
}
