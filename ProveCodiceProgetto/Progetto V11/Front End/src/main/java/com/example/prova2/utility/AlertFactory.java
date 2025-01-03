package com.example.prova2.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertFactory {

    private AlertFactory(){}
    public static Alert generateAlertSuccess(String scrittaBottone1, String scrittaBottone2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo Aggiunta Gestore!");
        alert.setHeaderText("Operazione completata");
        alert.setContentText("I dati sono stati salvati con successo!");

        ButtonType customYesButton = new ButtonType(scrittaBottone1, ButtonBar.ButtonData.NO);
        ButtonType customNoButton = new ButtonType(scrittaBottone2, ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(customYesButton, customNoButton);

        return alert;
    }

    public static Alert generateFailAlert(String titolo,String messaggio){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(titolo);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(messaggio);
        errorAlert.showAndWait();
        return errorAlert;
    }
}
