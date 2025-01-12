package com.example.prova2.controller;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.view.HomePage;
import com.example.prova2.view.LoginAmministratore;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import com.example.prova2.view.LoginAgenteImmobiliare;
import javafx.stage.Stage;


public class LoginHomeController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private Button buttonAgenteImmobiliare;

    @FXML
    private Button signInButton;

    @FXML
    private Button btnAmministratore;


    @FXML
    private void handleButtonAgente() throws IOException {
        LoginAgenteImmobiliare.initializePageLoginAgente(buttonAgenteImmobiliare.getScene().getWindow());
    }

    @FXML
    private void handleButtonAmministratore() throws IOException {
        LoginAmministratore.initializePageLoginAmministratore(btnAmministratore.getScene().getWindow());

    }


    @FXML
    private void handleSignIn() throws IOException, InterruptedException {

        String email = emailField.getText();
        String password = passwordField.getText();
        AccountSemplice accountSemplice = new AccountSemplice(email,password);

        emailErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);

        boolean valid = true;

        if (emailField.getText().trim().isEmpty()) {
            emailErrorLabel.setVisible(true);
            valid = false;
        }
        if (passwordField.getText().trim().isEmpty()) {
            passwordErrorLabel.setVisible(true);
            valid = false;
        }
        if (valid) {

        }
    }

    private void checkEsito(String esito) throws IOException {
        if (esito.equals("true")) {
            HomePage.caricamentoHome((Stage) signInButton.getScene().getWindow());
        }
    }
}