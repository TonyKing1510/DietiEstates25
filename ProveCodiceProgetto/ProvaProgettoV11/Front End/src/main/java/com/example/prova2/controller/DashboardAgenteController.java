package com.example.prova2.controller;
import com.example.prova2.view.CreaAnnuncioAgente;
import com.example.prova2.view.HomePage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardAgenteController {
    @FXML
    private VBox vbox1;
    @FXML
    private Button creannuncio;
    @FXML
    private Button btnHome;


    public void loadNotifications() throws IOException, InterruptedException {

    }

    @FXML
    private void handleCreaAnnuncio() throws IOException {
        CreaAnnuncioAgente.initializePageCreaAnnuncio(creannuncio.getScene().getWindow());
    }

    @FXML
    private void handleTornaHome(ActionEvent event) throws IOException {
        HomePage.initializeHomePage((Stage) btnHome.getScene().getWindow());
    }

}

