package com.example.prova2.controller;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.repository.LoginRequester;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

import com.example.prova2.view.LoginAgenteImmobiliare;



public class HomeController {

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
    private void handleButtonAgente() throws IOException {
        LoginAgenteImmobiliare.initializePageLoginAgente(buttonAgenteImmobiliare.getScene().getWindow());
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
            LoginRequester.request(accountSemplice);
            showAlert("Success", "User saved successfully.", AlertType.INFORMATION);
        } else {
            showAlert("Error", "Please fill in both username and password.", AlertType.ERROR);
        }
    }

    private void showAlert(String success, String s, AlertType alertType) {
        // Crea un oggetto Alert di tipo specificato (successo, errore, ecc.)
        Alert alert = new Alert(alertType);

        // Imposta il titolo della finestra di dialogo
        alert.setTitle(success);

        // L'intestazione (header) della finestra, qui non impostato (null)
        alert.setHeaderText(null);

        // Il messaggio che viene mostrato nel corpo dell'alert
        alert.setContentText(s);

        // Mostra l'alert e aspetta che l'utente lo chiuda
        alert.showAndWait();
    }
}