package com.example.prova2.Controller;

import com.example.prova2.Model.Notification;
import com.example.prova2.Utility.ButtonFactory;
import com.example.prova2.Utility.LabelFactory;
import com.example.prova2.Utility.PaneFactory;
import com.example.prova2.View.CreaAnnuncioAgente;
import com.example.prova2.requester.ReqNot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import  javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class ConsoleAgenteController {
    @FXML
    private VBox Vbox1;
    @FXML
    private Button creannuncio;

    @FXML
    private HBox HBox2;


    public void loadNotifications() throws IOException, InterruptedException {

        List<Notification> notifications = ReqNot.getNotifications();

        if(notifications!=null) {
            for(Notification notification : notifications) {
                Pane pane = PaneFactory.createPane();
                Button button = ButtonFactory.createButton();
                Label label = LabelFactory.createLabel(notification.getTitle());
                // Aggiunta del Label al Pane
                pane.getChildren().add(label);

                // Aggiunta del Pane come graphic del Button
                button.setGraphic(pane);

                // Aggiunta del Button al VBox
                Vbox1.getChildren().add(button);
            }
        }
    }

    @FXML
    private void handleCreaAnnuncio() throws IOException, InterruptedException {
        CreaAnnuncioAgente dashboardAgente = new CreaAnnuncioAgente(creannuncio.getScene().getWindow());
    }

    @FXML
    private void handleAggiungiImmagine() throws IOException, InterruptedException {

    }
}

