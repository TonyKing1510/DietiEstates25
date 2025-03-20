package com.example.prova2.controller.dashBoard;
import com.example.prova2.controller.modificaProfilo.ModificaProfiloAgenteController;
import com.example.prova2.controller.notifiche.VisualizzaNotificheClienteController;
import com.example.prova2.dto.ClienteDatiDTO;
import com.example.prova2.facade.ClienteServiceFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.Cliente;
import com.example.prova2.model.Utente;
import com.example.prova2.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class DashBoardClienteController {

    private static Cliente cliente = new Cliente();
    @FXML
    public Label labelNomeProf;
    @FXML
    public Label labelCognomeProf;
    @FXML
    public Label labelTel;
    @FXML
    public Label labelMail;
    @FXML
    public Label labelNome;
    @FXML
    public Label labelCognome;
    public Button btnLogout;

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        DashBoardClienteController.cliente = cliente;
    }

    public void initDati(Utente utente){
        new Thread(()->{ClienteDatiDTO dati=ClienteServiceFacade.prendiDati(utente);
            Platform.runLater(()->{
                Cliente cliente1 = new Cliente(dati.getNome(),dati.getCognome(),dati.getEmail(), dati.getTelefono());
                cliente1.setToken(utente.getToken());
                setCliente(cliente1);
                labelNomeProf.setText(dati.getNome());
                labelCognomeProf.setText(dati.getCognome());
                labelTel.setText(dati.getTelefono());
                labelMail.setText(dati.getEmail());
                labelNome.setText(dati.getNome());
                labelCognome.setText(dati.getCognome());
            });}).start();
    }

    public void apriPaginaCercaAnnunci() {
    }

    public void apriPaginaModificaProfilo() {
        try {
            System.out.println(cliente.getToken());
            ModificaProfiloAgenteController.setUtente(cliente);
            ModificaProfiloCliente.initializePageModificaProfiloCliente(labelCognome.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    public void apriPaginaVisualizzaNotifiche() {
        try {
            VisualizzaNotificheClienteController.setClienteNotifiche(cliente);
            VisualizzaNotificheCliente.initPage((Stage) labelNomeProf.getScene().getWindow());
        }catch (RuntimeException e){
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreInterno();
        }
    }

    public void vaiIndietro() {
        try{
            HomePage.caricamentoHome((Stage) btnLogout.getScene().getWindow(), cliente);
        }catch(IOException e){
            AlertFactory.generateFailAlertForErroreInterno();
        }
    }

    public void faiLogout() {
        try{
            System.out.println("ciao");
            DashBoardClienteController.setCliente(null);
            LoginPage.startMainApp((Stage) btnLogout.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }
}
