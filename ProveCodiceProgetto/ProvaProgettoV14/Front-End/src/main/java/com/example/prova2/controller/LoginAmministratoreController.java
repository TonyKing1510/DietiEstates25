package com.example.prova2.controller;
import com.example.prova2.model.AccountGestore;
import com.example.prova2.model.Agenzia;
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
import java.util.ArrayList;

public class LoginAmministratoreController {

    @FXML
    public Button bottoneTornaAllaHome;

    @FXML
    public Button bottoneAccedi;

    @FXML
    public RadioButton radioButtonAdmin;

    @FXML
    public RadioButton radioButtoneGestore;

    @FXML
    public Label compilaCodiceFiscale;

    @FXML
    public Label compilaSocieta;

    @FXML
    public Label compilaRadioButton;

    @FXML
    public Label compilaUsername;

    @FXML
    public Label compilaPassword;

    @FXML
    public MenuButton societaMenu;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public TextField cfTextField;

    @FXML
    public TextField usernameTextField;

    @FXML
    private Button bottoneRegistrati;

    protected static final String CFPATTERN = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";

    protected static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String USERNAMEPATTERN = "^.{6,}$";


    private static GestoreAgenziaImmobiliare adminDashBoard;


    public void initialize() {
        ArrayList<String> options = new ArrayList<>();
        aggiungiOpzioniArray(options);
        addListener(options);

    }

    private void addListener(ArrayList<String> options) {
        for (String option : options) {
            MenuItem menuItem = new MenuItem(option);
            menuItem.setOnAction(event -> {
                societaMenu.setText(option);
                compilaSocieta.setVisible(false);
            });
            societaMenu.getItems().add(menuItem);
        }
    }

    private void aggiungiOpzioniArray(ArrayList<String> options) {
        options.add("Tecnocasa");
        options.add("Bisonte");
    }


    @FXML
    public void registrati(ActionEvent actionEvent) {
            gestisciErroreMessaggioRadioButton();
            gestisciCampiVuoti();
            if(!isAlmenoUnCampoVuoto() && campiValidi()) {
                    System.out.println("campi validi");
                    inviaDati();
            }
    }

    private boolean gestisciCampiVuoti() {
        attivaMessaggioDiErroreCf();
        attivaMessaggioErroreUsername();
        attivaMessaggioDiErrorePassword();
        attivaMessaggioDiErroreSocieta();
        return isAlmenoUnCampoVuoto();
    }

    private boolean campiValidi() {
        boolean flag = true;
        flag &= codiceFiscaleNonValido();
        flag &= usernameNonValido();
        flag &= passwordNonValida();
        return flag;
    }

    private void inviaDati(){
        AccountGestore informazioniAccount = new AccountGestore(usernameTextField.getText().trim(),
                passwordTextField.getText());
        Agenzia agenziaAppartenente = new Agenzia(societaMenu.getText().trim());
        GestoreAgenziaImmobiliare gestoreAgenziaImmobiliare = new GestoreAgenziaImmobiliare(
                agenziaAppartenente
                ,cfTextField.getText().trim());

        gestoreAgenziaImmobiliare.setAccountGestore(informazioniAccount);
        isAdminGestore(gestoreAgenziaImmobiliare);


        gestisciLogin(gestoreAgenziaImmobiliare);
    }

    private void isAdminGestore(GestoreAgenziaImmobiliare gestoreAgenziaImmobiliare) {
        if(radioButtonAdmin.isSelected()) {
            gestoreAgenziaImmobiliare.setAdmin(true);
        }else if(radioButtoneGestore.isSelected()) {
            gestoreAgenziaImmobiliare.setAdmin(false);
        }
    }


    private void gestisciLogin(GestoreAgenziaImmobiliare g){
        if(isAndatoTuttoBene(g)) {
            apriPaginaDashBoard(g);
        }else{
            if(!LoginAdminService.isErroreConnessione()) {
                AlertFactory.generateFailAlert("Errore!", "Ci dispiace le tue credenziali sono errate prova a ricontrollare");
            }
            LoginAdminService.setErroreConnessione(false);
        }
    }

    private static boolean isAndatoTuttoBene(GestoreAgenziaImmobiliare g){
        return LoginAdminService.effettuaLoginAdmin(g);
    }

    private void apriPaginaDashBoard(GestoreAgenziaImmobiliare admin){
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

    public static void setAdminDash(GestoreAgenziaImmobiliare admin) {
        adminDashBoard = admin;
    }

    public static GestoreAgenziaImmobiliare getAdminDashBoard() {
        return adminDashBoard;
    }


    private boolean isAlmenoUnCampoVuoto() {
        return usernameVuoto() || passwordVuota() || cfVuoto() || societaVuota() || almenoUnRadioSelezionato();
    }

    private boolean almenoUnRadioSelezionato() {
        return !radioButtoneGestore.isSelected() && !radioButtonAdmin.isSelected();
    }

    private void attivaMessaggioErroreUsername() {
        compilaUsername.setVisible(!usernameNonValido());
    }

    private void attivaMessaggioDiErrorePassword() {
        compilaPassword.setVisible(!passwordNonValida());
    }

    private void attivaMessaggioDiErroreSocieta() {
        compilaSocieta.setVisible(societaVuota());
    }

    private void attivaMessaggioDiErroreCf() {
        compilaCodiceFiscale.setVisible(!codiceFiscaleNonValido());
    }

    private void gestisciErroreMessaggioRadioButton(){
        //attiva messaggio di errore radio solo quando non sono stati selezionati
        compilaRadioButton.setVisible(!radioButtonAdmin.isSelected() && !radioButtoneGestore.isSelected());
    }

    private boolean societaVuota() {
        return societaMenu.getText().equals("Seleziona la società");
    }

    private boolean passwordVuota() {
        return passwordTextField.getText().isEmpty();
    }

    private boolean usernameVuoto() {
        return usernameTextField.getText().isEmpty();
    }

    private boolean cfVuoto() {
        return cfTextField.getText().isEmpty();
    }

    @FXML
    public void attivoSoloRadioAdmin() {
        if(radioButtonAdmin.isSelected()){
            compilaRadioButton.setVisible(false);
            radioButtoneGestore.setSelected(false);
        }
    }

    @FXML
    public void attivoSoloRadioGestore() {
        if(radioButtoneGestore.isSelected()){
            compilaRadioButton.setVisible(false);
            radioButtonAdmin.setSelected(false);
        }
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

    public void eliminaMessaggioDiErrorecf() {
        compilaCodiceFiscale.setVisible(cfTextField.getText().isEmpty());

    }

    public void eliminaMessaggioDiErroreUsr() {
        compilaUsername.setVisible(usernameTextField.getText().isEmpty());
    }

    public boolean codiceFiscaleNonValido() {
        String cf = cfTextField.getText();
        if(cfVuoto()){
            return false;
        }
        if(!cf.matches(CFPATTERN)){
            attivaMessaggioDiErrore(compilaCodiceFiscale,"Si prega di inserire un codice fiscale valido!");
            return false;
        }else{
            compilaCodiceFiscale.setVisible(false);
            return true;
        }
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
