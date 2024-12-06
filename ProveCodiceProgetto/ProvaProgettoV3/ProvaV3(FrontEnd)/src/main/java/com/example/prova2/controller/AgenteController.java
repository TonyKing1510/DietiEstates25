package com.example.prova2.controller;

import com.example.prova2.view.DashboardAgente;
import com.example.prova2.view.LoginAgenteImmobiliare;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AgenteController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private TextField emailFieldAgente;

    @FXML
    private PasswordField passwordFieldAgente;

    @FXML
    private Button signInButtonAgente;

    @FXML
    private Button tornadietro;



    @FXML
    private void handleButtonAgente() throws IOException {
        // Ottieni lo stage corrente dal contesto della scena
        Stage currentStage = (Stage) emailField.getScene().getWindow();

        // Crea un'istanza di ViewManager e passa lo stage
        LoginAgenteImmobiliare loginAgenteImmobiliare = new LoginAgenteImmobiliare(currentStage);
    }

   /* @FXML
    private void handleTornaIndietro() throws IOException, InterruptedException {
        HomePageLogin home = new HomePageLogin(tornadietro.getScene().getWindow());


    }*/

    @FXML
    private void handleSignInAgente() throws IOException, InterruptedException {
        // Crea un'istanza di ViewManager e passa lo stage
        DashboardAgente dashboardAgente = new DashboardAgente(signInButtonAgente.getScene().getWindow());

    }
}
