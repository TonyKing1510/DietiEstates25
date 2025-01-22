package com.example.prova2.controller;
import com.example.prova2.facade.AuthServiceFacade;
import com.example.prova2.model.AccountAziendale;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.view.DashboardAgente;
import com.example.prova2.view.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.prova2.controller.RegistrazioneUtenteController.*;


public class LoginAgenteController {
    @FXML
    public TextField textFieldEmail;
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

    protected static final String EMAILPATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @FXML
    public Label errorUsername;

    @FXML
    public Label errorPassword;


    public void initialize(){
        sincronizzaPasswordFieldTextField();

    }

    private void sincronizzaPasswordFieldTextField() {
        mostraPa.textProperty().bindBidirectional(textFieldPassword.textProperty());
    }


    public void handelogin() {
        if(checkCampi()){
            inviaDati();
        }
    }

    private void inviaDati(){
        AccountSemplice accountSemplice = new AccountSemplice(textFieldEmail.getText().trim(), textFieldPassword.getText().trim());
        gestisciAuth(accountSemplice);
    }

    private void gestisciAuth(AccountSemplice accountSemplice){
        if(isAuthRiuscita(accountSemplice)) {
            apriDashBoardAgente();
        }
    }

    private static boolean isAuthRiuscita(AccountSemplice accountSemplice) {
        return AuthServiceFacade.loginAgente(accountSemplice.getMail(), accountSemplice.getPassword());
    }

    private boolean checkCampi() {
        boolean flag = true;
        flag&=checkUsername();
        flag&=checkPassword();
        return flag;
    }

    public boolean checkUsername() {
        String username = this.textFieldEmail.getText();
        return isValidUsername(username);
    }

    public boolean isValidUsername(String userName) {
        if (userName == null) {
            return false;
        }
        if(campoVuoto(userName)){scriviMessaggioErrore(errorUsername,"Si prega di inserire una email!");return false;}
        if(campoMatcha(userName, EMAILPATTERN)){
            levaMessaggioDiErrore(errorUsername);
            return true;
        }
        return inserisciCampoValido(errorUsername,"Si prega di inserire uno email valida!");
    }

    public boolean checkPassword() {
        String password = this.textFieldPassword.getText();
        return isValidPassword(password);
    }

    public boolean isValidPassword(String password) {
        return passwordValidation(password, errorPassword, PASSWORDPATTERN);
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
            DashboardAgenteController.setAgente(new AccountAziendale(textFieldEmail.getText().trim(), textFieldPassword.getText().trim()));
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
