package com.example.prova2.controller;

import com.example.prova2.model.AccountGestore;
import com.example.prova2.model.Admin;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.requester.AddGestoreRequester;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.view.CreaGestore;
import com.example.prova2.view.DashboardAmministratore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import com.example.prova2.controller.CreaGestoreController;

import java.io.IOException;
import java.util.Optional;

public class CreaAgenteImmobiliareController {
    public TextField textFieldNome;

    @FXML
    public TextField textFieldCognome;

    @FXML
    public TextField textFieldCf;

    @FXML
    public TextField textFieldNumeroTelefono;

    @FXML
    public TextField textFieldVia;

    @FXML
    public TextField textFieldNCivico;

    @FXML
    public Button bottoneSalvaDatiAna;

    @FXML
    public Label erroreNome;

    @FXML
    public Label erroreCodiceFiscale;

    @FXML
    public Label erroreCognome;

    @FXML
    public Label erroreNumeroTelefono;

    @FXML
    public Label erroreVia;

    @FXML
    public Label erroreNumeroCivico;

    @FXML
    public TextField textFieldUsername;

    @FXML
    public TextField textFieldEmail;

    @FXML
    public PasswordField textFieldPassword;

    @FXML
    public Label erroreUsername;

    @FXML
    public Label errorePassword;

    @FXML
    public Label erroreEmail;

    public AnchorPane labelDati;

    @FXML
    public PasswordField textFieldRiPassword;

    @FXML
    public Button tornaIndietroBottone;

    @FXML
    public Label errorepasswordNonUguale;

    @FXML
    public Label passwordLabel;

    @FXML
    public Label passwordReLabel;

    @FXML
    public Label emailLabel;

    @FXML
    public Label usernameLabel;

    @FXML
    public Label numeroTelefonoLabel;

    @FXML
    public Label viaLabel;

    @FXML
    public Label numeroCivicoLabel;

    @FXML
    public Label nomeLabel;

    @FXML
    public Label cognomeLabel;

    @FXML
    public Label cflabel;

    boolean bottoneCliccato = false;




    public  void salvaDati() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        if(checkCampi() && (creaGestoreController.checkDuplicati(textFieldEmail.getText(),textFieldUsername.getText(),textFieldCf.getText()))) {
            if (!bottoneCliccato) {
                doBottonePiuGrandeEVerde();
            }
            gestisciDoppioClick();
            bottoneCliccato = true;
        }
    }

    private boolean checkCampi() {
        boolean flag = true;
        flag &= checkDatiAccount();
        flag &= checkDatiAnagrafici();
        return flag;
    }

    private boolean checkDatiAnagrafici() {
        boolean flag = true; // Inizialmente true, e verrà impostato a false se una verifica fallisce
        flag &= checkNome();
        flag &= checkCognome();
        flag &= checkCf();
        flag &= checkTelefono();
        flag &= checkVia();
        flag &= checkNumeroCivico();
        return flag;

    }


    private boolean checkDatiAccount() {
        boolean flag = true;
        flag &= checkEmail();
        flag&= checkPassword();
        flag&= checkUsername();
        flag&= checkRePassword();
        return flag;
    }

    private void gestisciDoppioClick() {
        if(bottoneCliccato && (salvaDatiDopoDoppioClick())) {
            mostraPopUpConferma();
        }
    }

    private void mostraPopUpConferma() {
        Alert alert = AlertFactory.generateAlertSuccess("Torna alla dashboard","Continua a creare altri gestori");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().getText().equals("Torna alla dashboard")) {
                navigaAllaDashboard();
            } else if (result.get().getText().equals("Continua a creare altri gestori")) {
                navigaACreaGestore();
            }
        }
    }

    private void navigaAllaDashboard() {
        try {
            DashboardAmministratore.initializePageDashboardAmministratore(bottoneSalvaDatiAna.getScene().getWindow());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Operazione interrotta", "Il thread è stato interrotto durante l'operazione.");
        } catch (IOException e) {
            AlertFactory.generateFailAlert("Errore di navigazione", "Si è verificato un problema durante il caricamento della dashboard.");
        }
    }

    private void navigaACreaGestore() {
        try {
            CreaGestore.initializePageCreaGestore(bottoneSalvaDatiAna.getScene().getWindow());
        } catch (Exception e) {
            AlertFactory.generateFailAlert("Errore di navigazione", "Si è verificato un problema durante il caricamento della pagina di creazione gestori.");
        }
    }


    private void doBottonePiuGrandeEVerde() {
        bottoneSalvaDatiAna.setStyle(bottoneSalvaDatiAna.getStyle() + "-fx-background-color: green;");
        bottoneSalvaDatiAna.setPrefWidth(116);
        bottoneSalvaDatiAna.setPrefHeight(40);
    }



    private boolean salvaDatiDopoDoppioClick() {
        GestoreAgenziaImmobiliare gestore = new GestoreAgenziaImmobiliare(textFieldNome.getText(),textFieldCognome.getText()
                ,textFieldCf.getText(),textFieldNumeroTelefono.getText(),textFieldVia.getText(),textFieldNCivico.getText());
        AccountGestore accountGestore = new AccountGestore(textFieldUsername.getText(),textFieldPassword.getText()
                ,textFieldEmail.getText());
        Admin admin = DashboardAmministratoreController.getAdmin();

        gestore.setAccountGestore(accountGestore);
        gestore.setAdminAppartenente(admin);
        try {
            return AddGestoreRequester.addGestore(gestore);
        }catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }


    public void tornaIndietro(ActionEvent actionEvent) {
        try {
            DashboardAmministratore.initializePageDashboardAmministratore(tornaIndietroBottone.getScene().getWindow());
        } catch (IOException e){
            AlertFactory.generateFailAlert("Errore di navigazione","Si è verificato un errore ci scusi!");
            e.printStackTrace();
        } catch (InterruptedException e1){
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Errore di navigazione","Si è verificato un errore ci scusi!");
        }
    }

    public boolean checkEmail(){
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(textFieldEmail.getText(), CreaGestoreController.EMAILPATTERN, erroreEmail, "una email", emailLabel);
    }

    public boolean checkUsername(){
        String username = textFieldUsername.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(username, CreaGestoreController.USERNAMEPATTERN, erroreUsername, "uno username", usernameLabel);
    }


    public boolean checkPassword() {
        String password = textFieldPassword.getText();
        String passwordRe = textFieldRiPassword.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        creaGestoreController.isValidCampo(password,passwordRe,errorepasswordNonUguale,"due password uguali",passwordReLabel);
        return creaGestoreController.isValidCampo(password,CreaGestoreController.PASSWORDPATTERN,errorePassword,"una password",passwordLabel);
    }


    public boolean checkRePassword() {
        String password = textFieldPassword.getText();
        String passwordRinserita = textFieldRiPassword.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(password,passwordRinserita,errorepasswordNonUguale,"due password uguali",passwordReLabel);
    }


    public Boolean checkNome() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.controllaCampiAnagrafici(textFieldNome, erroreNome, CreaGestoreController.UNNOME);
    }

    public boolean checkCognome() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.controllaCampiAnagrafici(textFieldCognome, erroreCognome, CreaGestoreController.COGNOMEMSG);
    }

    public boolean checkCf() {
        String cf = textFieldCf.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(cf, CreaGestoreController.CFPATTERN, erroreCodiceFiscale, "un codice fiscale", cflabel);
    }

    public boolean checkTelefono() {
        String telefono = textFieldNumeroTelefono.getText();
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.isValidCampo(telefono,CreaGestoreController.TELEFONOPATTERN,erroreNumeroTelefono,"un numero di telefono",numeroTelefonoLabel);
    }

    public boolean checkVia() {
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        return creaGestoreController.controllaCampiAnagrafici(textFieldVia, erroreVia, CreaGestoreController.UNAVIA);
    }

    public boolean checkNumeroCivico(){
        CreaGestoreController creaGestoreController = new CreaGestoreController();
        String numeroCivico = textFieldNCivico.getText();
        return creaGestoreController.isValidCampo(numeroCivico,CreaGestoreController.NUMEROCIVICOPATTERN,erroreNumeroCivico,"un numero civico",numeroCivicoLabel);
    }





}
