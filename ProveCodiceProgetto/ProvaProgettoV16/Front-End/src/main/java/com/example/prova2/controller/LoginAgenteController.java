package com.example.prova2.controller;
import com.example.prova2.utility.AlertFactory;
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
    private void handleSignInAgente() {
        try {
            DashboardAgente.initializePageDashboardAgente(signInButtonAgente.getScene().getWindow());
        }catch (IOException e){
            AlertFactory.generateFailAlert("Errore!","Ci dispiace le tue credenziali sono errate prova a ricontrollare");
            e.printStackTrace();
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Errore!","Ci dispiace le tue credenziali sono errate prova a ricontrollare");
        }
    }

}
