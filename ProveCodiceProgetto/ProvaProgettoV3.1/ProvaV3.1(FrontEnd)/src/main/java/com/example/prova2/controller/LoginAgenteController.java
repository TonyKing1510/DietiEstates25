package com.example.prova2.controller;
import com.example.prova2.view.DashboardAgente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class LoginAgenteController {
    @FXML
    private TextField emailField;

    @FXML
    private Button signInButtonAgente;

    @FXML
    private Button tornadietro;




    @FXML
    private void handleSignInAgente() throws IOException, InterruptedException {
        DashboardAgente.initializePageDashboardAgente(signInButtonAgente.getScene().getWindow());
    }

}
