package com.example.prova2.controller;

import com.example.prova2.model.Notifica;
import com.example.prova2.utility.ButtonFactory;
import com.example.prova2.utility.LabelFactory;
import com.example.prova2.utility.PaneFactory;
import com.example.prova2.view.CreaAnnuncioAgente;
import com.example.prova2.repository.ReqNot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class DashboardAgenteController {
    @FXML
    private VBox vbox1;
    @FXML
    private Button creannuncio;



    public void loadNotifications() throws IOException, InterruptedException {

        List<Notifica> notifications = ReqNot.getNotifications();

        if(notifications!=null) {
            for(Notifica notification : notifications) {
                Pane pane = PaneFactory.createPane();
                Button button = ButtonFactory.createButton();
                Label label = LabelFactory.createLabel(notification.getDescrizioneNotifica());
                // Aggiunta del Label al Pane
                pane.getChildren().add(label);

                // Aggiunta del Pane come graphic del Button
                button.setGraphic(pane);

                // Aggiunta del Button al VBox
                vbox1.getChildren().add(button);
            }
        }
    }

    @FXML
    private void handleCreaAnnuncio() throws IOException {
        CreaAnnuncioAgente.initializePageCreaAnnuncio(creannuncio.getScene().getWindow());
    }
}

