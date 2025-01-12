package com.example.prova2.controller;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.service.AuthService;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.view.DashboardAgente;
import com.example.prova2.view.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginAgenteController {
    @FXML
    public TextField textFieldUsername;
    @FXML
    public Button loginButton;
    @FXML
    public Button tornaAllaHome;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public TextField mostraPa;
    @FXML
    public CheckBox mostraPasswordCheckBox;

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String USERNAMEPATTERN = "^.{6,}$";

    @FXML
    public Label errorUsername;

    @FXML
    public Label errorPassword;


    public void initialize(){
        sincronizzaPasswordFieldTextField();
        aggiungiPerdoFocusDaTextFieldUsername();
        aggiungiPerdoFocusDaTextFieldPassword();
    }

    private void sincronizzaPasswordFieldTextField() {
        mostraPa.textProperty().bindBidirectional(textFieldPassword.textProperty());
    }

    private void aggiungiPerdoFocusDaTextFieldPassword() {
        textFieldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuoto(textFieldPassword.getText()))) {
                    checkPassword();

            }
        });
        mostraPa.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuoto(textFieldPassword.getText()))) {
                    checkPassword();

            }
        });
    }

    private void aggiungiPerdoFocusDaTextFieldUsername() {
        textFieldUsername.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue) && (!isCampoVuoto(textFieldUsername.getText()))) {
                    checkUsername();
            }
        });
    }


    public void handelogin() {
        if(checkCampi()){
            inviaDati();
        }
    }

    private void inviaDati(){
        try {
            AccountSemplice accountSemplice = new AccountSemplice(textFieldUsername.getText().trim(), textFieldPassword.getText().trim());
            gestisciAuth(accountSemplice);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void gestisciAuth(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        if(isAuthRiuscita(accountSemplice))
        {
            apriDashBoardAgente();
        }else{
            AlertFactory.generateFailAlertForCredenzialiSbagliate();
        }
    }

    private static boolean isAuthRiuscita(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        return AuthService.requestLoginAgente(accountSemplice);
    }

    private boolean checkCampi() {
        boolean flag = true;
        flag&=checkUsername();
        flag&=checkPassword();
        return flag;
    }

    private boolean checkUsername() {
        String username = this.textFieldUsername.getText();
        return isVuoto(username,USERNAMEPATTERN, errorUsername);
    }

    private boolean checkPassword() {
        String password = this.textFieldPassword.getText();
        return isVuoto(password,PASSWORDPATTERN,errorPassword);
    }

    private boolean isVuoto(String campo,String pattern,Label error) {
        if(isCampoVuoto(campo)){
            attivaMessaggioDiErrore(error,"Si prega di compilare questo campo!");
            return false;
        }
        if(!campo.matches(pattern)) {
            attivaMessaggioDiErrore(error,"Si prega di inserire un campo valido!");
            return false;
        }
        disattivaMessaggioDiErrore(error);
        return true;
    }


    private void disattivaMessaggioDiErrore(Label errorLabel) {
        errorLabel.setVisible(false);
    }

    private static boolean isCampoVuoto(String campo) {
        return campo.isEmpty();
    }

    private void attivaMessaggioDiErrore(Label errorLabel, String s) {
        errorLabel.setVisible(true);
        errorLabel.setText(s);
    }


    public void gestisciMostraPass() {
        if (mostraPasswordCheckBox.isSelected()) {
            mostraPa.setVisible(true);
            mostraPa.setEditable(true);
            textFieldPassword.setVisible(false);
        } else {
            mostraPa.setVisible(false);
            mostraPa.setEditable(false);
            textFieldPassword.setVisible(true);
            textFieldPassword.setVisible(true);
        }
    }


    public void disattivaMessaggioErroreUsername(){
        errorUsername.setVisible(false);
    }

    public void disattivaMessaggioErrorePassword(){
        errorPassword.setVisible(false);
    }

    public void apriDashBoardAgente(){
        try{
            DashboardAgente.initializePageDashboardAgente(loginButton.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        } catch (InterruptedException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void tornaAllaHome(){
        try{
            LoginPage.startMainApp((Stage) tornaAllaHome.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }


}
