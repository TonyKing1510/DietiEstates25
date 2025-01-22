package com.example.prova2.controller;
import com.example.prova2.service.CheckPasswordService;
import com.example.prova2.service.UpdatePasswordService;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.view.DashboardAmministratore;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CambioPasswordController {

    @FXML
    public PasswordField passwordAttualeField;

    @FXML
    public PasswordField nuovaPasswordField;

    @FXML
    public PasswordField rinsertNuovaPassword;

    @FXML
    public Button salvaBottone;

    @FXML
    public Button tornaIndietroBottone;

    @FXML
    public Label errorreVecchiaPass;

    @FXML
    public Label erroreNuovaPass;

    @FXML
    public Label erroreRinserNuovaPass;

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    @FXML
    public Label labelNuovaPass;

    @FXML
    public Label labelInsertNuovaPass;



    public void cambiaPassword(){
        if(checkCampi()){
            if(isCambioAvvenuto()) {
                mostrapopupconfermaPassword();
            }
            else{
                mostraPopUpErrorePassword();
            }
        }
    }

    private static void mostraPopUpErrorePassword() {
        Alert alert = AlertFactory.generateFailAlert("Errore nel cambio della password!","Non è stato possibile cambiare la password.Siamo spiacenti si è verificato un errore durante l'aggiornamento della password.");
        alert.showAndWait();
    }

    private static void mostrapopupconfermaPassword() {
        Alert alert=AlertFactory.generateAlertSuccess("Torna alla home","Torna Dashboard","Operazione completata!","La password è stata cambiata con successo.","Complimenti! La password è stata aggiornata con successo.\nOra potrai utilizzare la nuova password per accedere a tutti i \nservizi di DietiEstates.");
        alert.showAndWait();
    }

    private boolean isCambioAvvenuto() {
        return UpdatePasswordService.updatePassword(nuovaPasswordField.getText(), DashboardAmministratoreController.getAdmin());
    }


    public boolean checkCampi(){
        boolean flag = true;
        flag &= checkVecchiaPassword();
        flag &= checkNuovaPass();
        flag &= checkRePassword();
        return flag;
    }

    public boolean checkVecchiaPassword() {
        String password = passwordAttualeField.getText();
        if(isPasswordVecchiaValida(password)){
            errorreVecchiaPass.setVisible(false);
            return true;
        }
        else{
            if(password.isEmpty()){
                scriviMessaggioErrore("Si prega di inserire una password!");
            }else{
                scriviMessaggioErrore("Ci dispiace ma la password inserita non corrisponde alla password attuale!");
            }
            errorreVecchiaPass.setVisible(true);
            return false;
        }
    }

    private void scriviMessaggioErrore(String s) {
        errorreVecchiaPass.setText(s);
    }

    private static boolean isPasswordVecchiaValida(String password) {
        return CheckPasswordService.checkPassword(DashboardAmministratoreController.getAdmin(), password);
    }

    public boolean checkNuovaPass() {
        CreaGestoreController c = new CreaGestoreController();
        attivaErrorePasswordUguale();
        //return c.isValidCampo(nuovaPasswordField.getText(),PASSWORDPATTERN,erroreNuovaPass,"una password!",labelNuovaPass);
        return true;
    }

    private void attivaErrorePasswordUguale() {
        if(!nuovaPasswordField.getText().equals(rinsertNuovaPassword.getText())) {
            erroreRinserNuovaPass.setText("Si prega di inserire due password uguali!");
            erroreRinserNuovaPass.setVisible(true);
        }else{
            erroreRinserNuovaPass.setVisible(false);
        }
    }

    public boolean checkRePassword() {
        if(passwordNuovaERinsertSonoUguali()){
            erroreRinserNuovaPass.setVisible(false);
            return true;
        }else{
            erroreRinserNuovaPass.setText("Si prega di inserire due password uguali!");
            erroreRinserNuovaPass.setVisible(true);
            return false;
        }
    }

    private boolean passwordNuovaERinsertSonoUguali() {
        return rinsertNuovaPassword.getText().equals(nuovaPasswordField.getText());
    }


    public void tornaDash() {
        try{
            DashboardAmministratore.initializePageDashboardAmministratore(tornaIndietroBottone.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert("Errore nella navigazione","Ci dispiace si è verificato un errore nel caricamento della dashboard");
        } catch (InterruptedException e) {
            AlertFactory.generateFailAlert("Errore nella navigazione","Ci dispiace si è verificato un errore nel caricamento della dashboard");
            Thread.currentThread().interrupt();
        }
    }

    public void levaMessaggioDiErroreVecchiaPass() {
        errorreVecchiaPass.setVisible(false);
    }

    public void levaMessaggioDiErroreNuovaPass() {
        erroreNuovaPass.setVisible(false);
    }

    public void levaMessaggioDiErroreRinserNuovaPass() {
        erroreRinserNuovaPass.setVisible(false);
    }
}
