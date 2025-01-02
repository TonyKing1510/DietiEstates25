package com.example.prova2.controller;
import com.example.prova2.model.AccountAmministratore;
import com.example.prova2.model.Admin;
import com.example.prova2.requester.LoginAdminRequester;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.view.DashboardAmministratore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginAmministratoreController {
    @FXML
    public TextField codiceFiscale;

    @FXML
    public Button bottoneTornaAllaHome;

    @FXML
    public Button bottoneAccedi;

    @FXML
    public RadioButton radioButtonAdmin;

    @FXML
    public RadioButton radioButtoneGestore;

    @FXML
    public TextField nome;

    @FXML
    public TextField cognome;

    @FXML
    public TextField societa;

    @FXML
    public Label compilaNome;

    @FXML
    public Label compilaCognome;

    @FXML
    public Label compilaEmail;

    @FXML
    public Label compilaCodiceFiscale;

    @FXML
    public Label compilaSocieta;

    @FXML
    public Label compilaRadioButton;

    @FXML
    private Button bottoneRegistrati;

    @FXML
    private TextField email;

    private static Admin adminDashBoard;



    @FXML
    public void registrati(ActionEvent actionEvent) throws IOException, InterruptedException {
        gestisciErroreMessaggioRadioButton();
        gestisciCampiVuoti();
        AccountAmministratore accountAmministratore = new AccountAmministratore(email.getText());
        Admin admin = new Admin(nome.getText(),cognome.getText(),societa.getText(), codiceFiscale.getText());
        admin.setAccountAmministratore(accountAmministratore);
        isAdminSelected(admin);
        LoginAdminRequester loginAdminRequester = new LoginAdminRequester();
        gestisciLogin(loginAdminRequester, admin);
    }


    private void gestisciLogin(LoginAdminRequester loginAdminRequester, Admin admin) throws IOException, InterruptedException {
        if(controllaEsito(loginAdminRequester.effettuaLoginAdmin(admin))){
            gestisciAccessoAllaDashBoard(admin);
        }
    }

    private void gestisciAccessoAllaDashBoard(Admin admin){
        setAdminDash(admin);
        DashboardAmministratoreController.setAdmin(admin);

        try {
            DashboardAmministratore.initializePageDashboardAmministratore(bottoneRegistrati.getScene().getWindow());
        }catch (IOException e){
            AlertFactory.generateFailAlert("Errore di navigazione",
                    "Si è verificato un problema durante il caricamento della dashboard.");
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Operazione interrotta", "Il thread è stato interrotto durante l'operazione.");
        }
    }

    public static void setAdminDash(Admin admin) {
        adminDashBoard = admin;
    }

    public static Admin getAdminDashBoard() {
        return adminDashBoard;
    }

    private boolean controllaEsito(String esito) {
        return esito.equals("true");
    }

    private void isAdminSelected(Admin admin) {
        if(radioButtonAdmin.isSelected()) {
            admin.setAdmin(true);
        }
    }

    private void gestisciCampiVuoti() {
        attivaMessaggioErroreNome();
        attivaMessaggioDiErroreCognome();
        attivaMessaggioDiErroreEmail();
        attivaMessaggioDiErrorePartitaIva();
        attivaMessaggioDiErroreSocieta();
    }

    private void attivaMessaggioErroreNome() {
        compilaNome.setVisible(nomeVuoto());
    }

    private void attivaMessaggioDiErroreEmail() {
        compilaEmail.setVisible(emailVuota());
    }

    private void attivaMessaggioDiErroreSocieta() {
        compilaSocieta.setVisible(societaVuota());
    }

    private void attivaMessaggioDiErrorePartitaIva() {
        compilaCodiceFiscale.setVisible(partitaIvaVuota());
    }


    private void attivaMessaggioDiErroreCognome() {
        compilaCognome.setVisible(cognomeVuoto());
    }

    private void gestisciErroreMessaggioRadioButton(){
        //attiva messaggio di errore radio solo quando non sono stati selezionati
        compilaRadioButton.setVisible(!radioButtonAdmin.isSelected() && !radioButtoneGestore.isSelected());
    }

    private boolean societaVuota() {
        return societa.getText().isEmpty();
    }

    private boolean partitaIvaVuota() {
        return codiceFiscale.getText().isEmpty();
    }

    private boolean emailVuota() {
        return email.getText().isEmpty();
    }

    private boolean cognomeVuoto() {
        return cognome.getText().isEmpty();
    }

    private boolean nomeVuoto() {
        return nome.getText().isEmpty();
    }

    @FXML
    public void attivoSoloRadioAdmin(ActionEvent actionEvent) {
        if(radioButtonAdmin.isSelected()){
            radioButtoneGestore.setSelected(false);
        }
    }

    @FXML
    public void attivoSoloRadioGestore(ActionEvent actionEvent) {
        if(radioButtoneGestore.isSelected()){
            radioButtonAdmin.setSelected(false);
        }
    }
}
