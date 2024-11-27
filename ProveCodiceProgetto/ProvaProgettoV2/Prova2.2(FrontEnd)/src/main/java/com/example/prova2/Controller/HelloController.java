package com.example.prova2.Controller;

import com.example.prova2.Model.User;
import com.example.prova2.requester.Requester;
import com.example.prova2.View.*;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import com.example.prova2.View.ViewManagerAgente;



public class HelloController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private void handleButtonAgente() throws IOException, InterruptedException {
        // Ottieni lo stage corrente dal contesto della scena
        Stage currentStage = (Stage) emailField.getScene().getWindow();

        // Crea un'istanza di ViewManager e passa lo stage
       ViewManagerAgente viewManager = new ViewManagerAgente(currentStage);

        // Passa il nome del file FXML per la nuova scena
        viewManager.changeSceneWithFade("/com/example/prova2/ProvaLoginAgente.fxml");
    }




    @FXML
    private void handleSignIn() throws IOException, InterruptedException {

        String email = emailField.getText();
        String password = passwordField.getText();
        User user = new User(email, password);

        //User user1 = new User(email,password);

        // Reset messaggi di errore
        emailErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);

        // Flag di validazione
        boolean valid = true;

        // Verifica se l'email è vuota
        if (emailField.getText().trim().isEmpty()) {
            emailErrorLabel.setVisible(true);
            valid = false;
        }

        // Verifica se la password è vuota
        if (passwordField.getText().trim().isEmpty()) {
            passwordErrorLabel.setVisible(true);
            valid = false;
        }

        // Se i campi sono completi, procedi con l'autenticazione
        if (valid) {
            Requester.request(user);
            showAlert("Success", "User saved successfully.", AlertType.INFORMATION);
        } else {
            // Se i dati non sono validi, mostra un messaggio di errore
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