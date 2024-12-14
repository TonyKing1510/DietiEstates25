package com.example.prova2.controller;

import com.example.prova2.view.DashboardAgente;
import com.example.prova2.view.DashboardAmministratore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoginAmministratoreController {
    @FXML
    private Button signInButtonAmministratore;

    @FXML
    private void handleSignInAmministratore() throws IOException, InterruptedException  {
        DashboardAmministratore.initializePageDashboardAmministratore(signInButtonAmministratore.getScene().getWindow());
    }

}
