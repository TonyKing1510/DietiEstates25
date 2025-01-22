package com.example.prova2.controller;
import com.example.prova2.dto.GestoreAgenziaImmobiliareDTO;
import com.example.prova2.facade.AuthServiceFacade;
import com.example.prova2.model.AccountGestore;
import com.example.prova2.model.Agenzia;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.view.DashboardAmministratore;
import com.example.prova2.view.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AccediAdminController {

    @FXML
    public Button bottoneTornaAllaHome;
    @FXML
    public TextField usernameTextField;
    @FXML
    public Label errorLabel;
    @FXML
    public Button bottoneAccedi;
    @FXML
    public Label compilaPassword;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button pulisciCampi;

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String EMAILPATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    public void apriDashBoard(AccountGestore accountGestore, GestoreAgenziaImmobiliareDTO dto) {
        try {
            GestoreAgenziaImmobiliare gestore = creaGestore(accountGestore, dto);
            DashboardAmministratoreController.setAdmin(gestore);
            DashboardAmministratore.initializePageDashboardAmministratore(bottoneAccedi.getScene().getWindow());
        } catch (IOException | InterruptedException e) {
            gestisciErroreCaricamentoDashboard(e);
        }
    }

    private GestoreAgenziaImmobiliare creaGestore(AccountGestore accountGestore, GestoreAgenziaImmobiliareDTO dto) {
        GestoreAgenziaImmobiliare gestore = new GestoreAgenziaImmobiliare();
        gestore.setAccountGestore(accountGestore);
        gestore.setAdmin(dto.isAdditionalInfo());
        gestore.setCf(dto.getCf());
        gestore.setAgenziaAppartenente(new Agenzia(dto.getAgenzia()));
        return gestore;
    }

    private void gestisciErroreCaricamentoDashboard(Exception e) {
        AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        if (e instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        e.printStackTrace();
    }

    public void eliminaMessaggioDiErroreUsr() {
        errorLabel.setText("");
    }

    public void eliminaMessaggioDiErrorePass() {
        compilaPassword.setText("");
    }

    public void tornaHome() {
        try{
            LoginPage.startMainApp((Stage) bottoneTornaAllaHome.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            e.printStackTrace();
        }
    }

    public void accedi() {
        if (!controllaCampi()) {
            return;
        }
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        AuthServiceFacade facade = new AuthServiceFacade();
        GestoreAgenziaImmobiliareDTO dto = facade.loginAdmin(username, password);
        if(dto != null){
            apriDashBoard(new AccountGestore(username,password),dto);
        }
    }



    public boolean controllaCampi() {
        boolean flag = true;
        flag&=checkCampo(usernameTextField.getText(), errorLabel, EMAILPATTERN,"Si prega di inserire una email valida!");
        flag&=checkCampo(passwordTextField.getText(),compilaPassword,PASSWORDPATTERN,"Si prega di inserire una password valida!");
        return flag;
    }

    public boolean checkCampo(String username,Label errorLabel,String pattern,String message) {
        if(username.matches(pattern)) {
            return true;
        }else{
            if(username.isEmpty()) {
                errorLabel.setVisible(true);
                errorLabel.setText("Si prega di compilare questo campo!");
            }else{
                errorLabel.setVisible(true);
                errorLabel.setText(message);
            }
            return false;
        }
    }
}
