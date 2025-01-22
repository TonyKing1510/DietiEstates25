package com.example.prova2.controller;
import com.example.prova2.facade.AuthServiceFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.view.AccediAdmin;
import com.example.prova2.view.HomePage;
import com.example.prova2.view.RegistrazioneUtente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import com.example.prova2.view.LoginAgenteImmobiliare;
import javafx.stage.Stage;

import static com.example.prova2.controller.CreaAgenteImmobiliareController.levaMessaggioDiErrore;


public class LoginHomeController {

    public Label registratiBotton;
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

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String EMAILPATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    @FXML
    private void handleButtonAgente() throws IOException {
        LoginAgenteImmobiliare.initializePageLoginAgente(buttonAgenteImmobiliare.getScene().getWindow());
    }

    @FXML
    private void handleButtonAmministratore() throws IOException {
        AccediAdmin.initializePageAccediAdmin(btnAmministratore.getScene().getWindow());

    }


    @FXML
    private void handleSignIn(){
        boolean flag=true;
        flag&=checkEmail();
        flag&=checkPassword();
        if(campiValidi(flag) && (credenzialiEsatte())) {
                apriHome();
        }
    }

    public void apriPaginaRegistrati(){
        try {
            RegistrazioneUtente.initializePageRegistrazioneUtente(registratiBotton.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    private void apriHome(){
        try {
            HomePage.caricamentoHome((Stage) signInButton.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    private boolean credenzialiEsatte() {
        return AuthServiceFacade.loginCliente(emailField.getText().trim(), passwordField.getText().trim());
    }

    private static boolean campiValidi(boolean flag) {
        return flag;
    }

    private boolean checkEmail(){
        return CreaAgenteImmobiliareController.isValidEmail(emailField.getText(),emailErrorLabel,EMAILPATTERN);
    }

    private boolean checkPassword(){
        return CreaAgenteImmobiliareController.isValidPassword(passwordField.getText(),passwordErrorLabel,PASSWORDPATTERN);
    }

    public void levaMessaggioDiErrorePassword() {
        levaMessaggioDiErrore(passwordErrorLabel);
    }

    public void levaMessaggioDiErroreEmail() {
        levaMessaggioDiErrore(emailErrorLabel);
    }

    private void checkEsito(String esito) throws IOException {
        if (esito.equals("true")) {
            HomePage.caricamentoHome((Stage) signInButton.getScene().getWindow());
        }
    }
}