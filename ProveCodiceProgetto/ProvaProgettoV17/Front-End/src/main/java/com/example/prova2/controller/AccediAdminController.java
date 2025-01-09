package com.example.prova2.controller;
import com.example.prova2.model.AccountGestore;

import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.service.LoginAdminService;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.view.DashboardAmministratore;
import com.example.prova2.view.LoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AccediAdminController {

    @FXML
    public Button bottoneTornaAllaHome;


    @FXML
    public Label compilaUsername;

    @FXML
    public Label compilaPassword;


    @FXML
    public PasswordField passwordTextField;


    @FXML
    public TextField usernameTextField;

    @FXML
    private Button bottoneRegistrati;

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String USERNAMEPATTERN = "^.{6,}$";



    private boolean isAlmenoUnCampoVuoto() {
        return usernameVuoto() || passwordVuota() ;
    }


    private void attivaMessaggioErroreUsername() {
        compilaUsername.setVisible(!usernameNonValido());
    }

    private void attivaMessaggioDiErrorePassword() {
        compilaPassword.setVisible(!passwordNonValida());
    }


    private boolean passwordVuota() {
        return passwordTextField.getText().isEmpty();
    }

    private boolean usernameVuoto() {
        return usernameTextField.getText().isEmpty();
    }


    public void tornaHome() {
        try{
            LoginPage.startMainApp((Stage) bottoneTornaAllaHome.getScene().getWindow());
        } catch (IOException e){
            AlertFactory.generateFailAlert("Errore!","Ci dispiace si è verificato un errore nel tornare alla home!");
        }
    }

    public void eliminaMessaggioDiErrorePass() {
        compilaPassword.setVisible(passwordTextField.getText().isEmpty());
    }



    public void eliminaMessaggioDiErroreUsr() {
        compilaUsername.setVisible(usernameTextField.getText().isEmpty());
    }


    public boolean passwordNonValida(){
        String password = passwordTextField.getText();
        if(passwordVuota()){
            return false;
        }
        if(!password.matches(PASSWORDPATTERN)){
            attivaMessaggioDiErrore(compilaPassword,"Si prega di inserire una password valida!");
            return false;
        }else{
            compilaPassword.setVisible(false);
            return true;
        }
    }

    public boolean usernameNonValido(){
        String username = usernameTextField.getText();
        if(usernameVuoto()){
            return false;
        }
        if(!username.matches(USERNAMEPATTERN)){
            attivaMessaggioDiErrore(compilaUsername,"Si prega di inserire uno username valido!");
            return false;
        }else{
            compilaUsername.setVisible(false);
            return true;
        }
    }

    private void attivaMessaggioDiErrore(Label erroreLabel,String message){
        erroreLabel.setVisible(true);
        erroreLabel.setText(message);
    }
}

