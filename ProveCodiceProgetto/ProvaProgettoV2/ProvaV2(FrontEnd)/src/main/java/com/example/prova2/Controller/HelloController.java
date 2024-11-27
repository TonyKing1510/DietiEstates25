package com.example.prova2.Controller;

import com.example.prova2.Model.User;
import com.example.prova2.requester.Requester;
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
        // Carica il nuovo file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/prova2/ProvaLoginAgente.fxml"));
        Parent root = loader.load();

// Ottieni lo stage attuale dalla scena corrente
        Stage currentStage = (Stage) emailField.getScene().getWindow();

// Crea la transizione Fade Out (fade verso trasparente)
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), currentStage.getScene().getRoot());
        fadeOut.setFromValue(1.0);  // Partenza completamente visibile
        fadeOut.setToValue(0.0);    // Fine completamente trasparente
        fadeOut.setOnFinished(event -> {
            // Cambia la scena al termine del fade out
            Scene newScene = new Scene(root, 1280, 800);
            currentStage.setScene(newScene);
            currentStage.setTitle("Agente Immobiliare Dashboard");

            // Crea la transizione Fade In (ripristina visibilità)
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
            fadeIn.setFromValue(0.0);  // Partenza trasparente
            fadeIn.setToValue(1.0);    // Fine completamente visibile
            fadeIn.play();
        });

// Avvia la transizione di Fade Out
        fadeOut.play();

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