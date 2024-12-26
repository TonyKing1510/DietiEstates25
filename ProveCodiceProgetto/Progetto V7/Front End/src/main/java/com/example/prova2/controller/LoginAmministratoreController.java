package com.example.prova2.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginAmministratoreController {
    @FXML
    public TextField partitaIva;

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
    public Label compilaPartitaIva;

    @FXML
    public Label compilaSocieta;

    @FXML
    private Button bottoneRegistrati;

    @FXML
    private TextField email;


    @FXML
    public void registrati(ActionEvent actionEvent) {
        if(campivuoti()){
            compilaNome.setVisible(true);
            compilaCognome.setVisible(true);
            compilaEmail.setVisible(true);
            compilaPartitaIva.setVisible(true);
            compilaSocieta.setVisible(true);
        }
    }

    private boolean campivuoti() {
        return nomeVuoto() && cognomeVuoto() && emailVuota() && partitaIvaVuota() && societaVuota();
    }

    private boolean societaVuota() {
        return societa.getText().isEmpty();
    }

    private boolean partitaIvaVuota() {
        return partitaIva.getText().isEmpty();
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
