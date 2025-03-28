package com.example.prova2.controller;
import com.example.prova2.dto.ClienteDTO;
import com.example.prova2.facade.ClienteServiceFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.model.Cliente;
import com.example.prova2.view.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrazioneUtenteController {
    @FXML
    public TextField textFieldNome;
    @FXML
    public TextField textFieldCognome;
    @FXML
    public TextField textFieldTelefono;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public Label erroreNome;
    @FXML
    public Label erroreCognome;
    @FXML
    public Label erroreTelefono;
    @FXML
    public Label erroreMail;
    @FXML
    public Label errorePassword;

    private static final String PASSWORDPATTERN = "^.{8,}$";

    protected static final String EMAIL_PATTERN="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final String NAME_PATTERN = "^[a-zA-ZàèéìòùÀÈÉÌÒÙ'\\-\\s]{2,}$";

    private static final String PHONE_PATTERN = "^\\d{10}$";
    @FXML
    public PasswordField passwordField;

    @FXML
    public Button bottoneTornaAllaHome;


    public void registraCliente() {
        if(checkCampi()){
            AccountSemplice accountSemplice = new AccountSemplice(null,passwordField.getText().trim(),textFieldEmail.getText().trim());
            ClienteDTO dto=ClienteServiceFacade.registrati(new Cliente(textFieldNome.getText().trim(), textFieldCognome.getText().trim(),accountSemplice,textFieldTelefono.getText()));
            if(dto!=null) {
                isErroreInEmail(dto);
            }
        }
    }

    public void tornaAllaHome(){
        try {
            LoginPage.startMainApp((Stage) bottoneTornaAllaHome.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    private void isErroreInEmail(ClienteDTO dto) {
        if(dto.isEmailNonEsistente()){
            erroreMail.setVisible(true);
            erroreMail.setText("Si prega di inserire una email esistente!");
        }
        if(dto.isDuplicato()){
            erroreMail.setVisible(true);
            erroreMail.setText("L'email inserita è già in uso.Si prega di inserire un'altra email!");
        }
    }


    public boolean checkCampi() {
        boolean flag = true;
        flag&=isValidName(textFieldNome.getText());
        flag&=isValidCognome(textFieldCognome.getText());
        flag&=isValidPassword(passwordField.getText().trim());
        flag&=isValidEmail(textFieldEmail.getText().trim());
        flag&=isValidPhone(textFieldTelefono.getText().trim());
        return flag;
    }

    public void levaMessaggioDiErroreNome() {
        levaMessaggioDiErrore(erroreNome);
    }

    public void levaMessaggioDiErroreCognome() {
        levaMessaggioDiErrore(erroreCognome);
    }

    public void levaMessaggioDiErroreTelefono() {
        levaMessaggioDiErrore(erroreTelefono);
    }

    public void levaMessaggioDiErroreEmail() {
        levaMessaggioDiErrore(erroreMail);
    }

    public void levaMessaggioDiErrorePassword() {
        levaMessaggioDiErrore(errorePassword);
    }

    public boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        if(campoVuoto(name)){scriviMessaggioErrore(erroreNome,"Si prega di inserire un nome!");return false;}
        if(campoMatcha(name,NAME_PATTERN)){
            levaMessaggioDiErrore(erroreNome);
            return true;
        }
        return inserisciCampoValido(erroreNome,"Si prega di inserire un nome valido!");
    }

    public static boolean inserisciCampoValido(Label label,String s) {
        scriviMessaggioErrore(label,s);
        return false;
    }

    public static boolean campoMatcha(String name,String pattern) {
        return name.matches(pattern);
    }

    public static void scriviMessaggioErrore(Label label,String text){
        label.setText(text);
        label.setVisible(true);
    }

    public boolean isValidCognome(String cognome) {
        if (cognome == null) {
            return false;
        }
        if(campoVuoto(cognome)){scriviMessaggioErrore(erroreCognome,"Si prega di inserire un cognome!");return false;}
        if(campoMatcha(cognome,NAME_PATTERN)){
            levaMessaggioDiErrore(erroreCognome);
            return true;
        }
        return inserisciCampoValido(erroreCognome,"Si prega di inserire un cognome valido!");
    }

    public boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        if(campoVuoto(phone)){scriviMessaggioErrore(erroreTelefono,"Si prega di inserire un numero di telefono!");return false;}
        if(campoMatcha(phone,PHONE_PATTERN)){
            levaMessaggioDiErrore(erroreTelefono);
            return true;
        }
        return inserisciCampoValido(erroreTelefono,"Si prega di inserire un telefono valido!");
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        if(campoVuoto(email)){scriviMessaggioErrore(erroreMail,"Si prega di inserire una email!");return false;}
        if(campoMatcha(email,EMAIL_PATTERN)){
            levaMessaggioDiErrore(erroreMail);
            return true;
        }
        return inserisciCampoValido(erroreMail,"Si prega di inserire una email valida!");
    }

    public boolean isValidPassword(String password) {
        return passwordValidation(password, errorePassword, PASSWORDPATTERN);
    }

    static boolean passwordValidation(String password, Label errorePassword, String passwordpattern) {
        if (password == null) {
            return false;
        }
        if(campoVuoto(password)){scriviMessaggioErrore(errorePassword,"Si prega di inserire una password!");return false;}
        if(campoMatcha(password, passwordpattern)){
            levaMessaggioDiErrore(errorePassword);
            return true;
        }
        return inserisciCampoValido(errorePassword,"Si prega di inserire una password valida!");
    }


    public static boolean campoVuoto(String campo){
        return campo.isEmpty();
    }

    public static void levaMessaggioDiErrore(Label label){
        label.setVisible(false);
    }
}
