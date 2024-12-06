package com.example.prova2.controller;

import com.example.prova2.model.Notification;
import com.example.prova2.utility.ButtonFactory;
import com.example.prova2.utility.LabelFactory;
import com.example.prova2.utility.PaneFactory;
import com.example.prova2.repository.ReqNot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class NotificationController {

    @FXML
    private Button Bottone1;

    @FXML
    private Label Label1;

    @FXML
    private VBox Vbox1;

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
}

