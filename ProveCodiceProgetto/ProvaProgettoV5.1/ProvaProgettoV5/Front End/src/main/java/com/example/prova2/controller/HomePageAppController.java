package com.example.prova2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class HomePageAppController {
    @FXML
    private ScrollPane ScrollPaneHome;
    @FXML
    private ScrollPane principalScrollPane;

    @FXML
    private Button btnAvanti;

    @FXML
    private Button btnIndietro;

    @FXML
    private Pane AnnunciPane;
    @FXML
    private Pane principalPane;
    @FXML
    private Pane AgenziePane;
    @FXML
    private Pane NewsPane;

    @FXML
    private void handleAvanti() {
        double currentHvalue = ScrollPaneHome.getHvalue();
        ScrollPaneHome.setHvalue(Math.min(1, currentHvalue + 0.2));
    }

    @FXML
    private void handleIndietro() {
        double currentHvalue = ScrollPaneHome.getHvalue();
        ScrollPaneHome.setHvalue(Math.max(0, currentHvalue - 0.2));
    }

    @FXML
    private void scrollToPane(ActionEvent event){
        // Calcolare la posizione verticale di "myPane" rispetto al contenuto totale e impostare lo scroll
        double targetPosition = AnnunciPane.getLayoutY() / principalPane.getHeight();
        principalScrollPane.setVvalue(targetPosition);

    }

    @FXML
    private void scrollToPaneAgenzie(ActionEvent event){
        double offset = 0.1;
        // Calcolare la posizione verticale di "myPane" rispetto al contenuto totale e impostare lo scroll
        double targetPosition = (AgenziePane.getLayoutY() / principalPane.getHeight())+offset;

        principalScrollPane.setVvalue(targetPosition);

    }

    @FXML
    private void scrollToPaneNews(ActionEvent event){
        double offset = 0.1;
        double targetPosition = (NewsPane.getLayoutY() / principalPane.getHeight())+offset;
        principalScrollPane.setVvalue(targetPosition);
    }

}
