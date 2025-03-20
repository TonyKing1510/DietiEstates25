package com.example.prova2.controller.modificaProfilo;
import com.example.prova2.controller.dashBoard.DashBoardClienteController;
import com.example.prova2.facade.ClienteServiceFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.Account;
import com.example.prova2.model.Cliente;
import com.example.prova2.model.Utente;
import com.example.prova2.service.UpdatePasswordService;
import com.example.prova2.view.CambioPassword;
import com.example.prova2.view.DashBoardCliente;
import javafx.application.Platform;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ModificaProfiloClienteController extends ModificaProfiloAgenteController {

    @Override
    public void salvaDati() {
        if(checkCampi()){
            Cliente clienteNuovo = new Cliente(textFieldNome.getText(),textFieldCognome.getText(),textFieldEmail.getText(),textFieldNumeroTelefono.getText());
            ClienteServiceFacade.updateDatiCliente(clienteNuovo,utente);
            aggiornaClienteProfilo(clienteNuovo);
            initProfilo();
        }
    }

    private void aggiornaClienteProfilo(Cliente nuovoCliente) {
        utente.setAccountAgente(new Account(nuovoCliente.getAccountAgente().getEmail()));
        utente.setNome(nuovoCliente.getNome());
        utente.setCognome(nuovoCliente.getCognome());
        utente.setTelefono(nuovoCliente.getTelefono());
    }

    @Override
    public void initProfilo() {
        CompletableFuture.supplyAsync(() -> {
                    initTextField();
                    DashBoardClienteController.setCliente((Cliente) utente);
                    return DashBoardClienteController.getCliente();
                })
                .thenAccept(clienteNuovo -> Platform.runLater(() -> {
                    setDatiOnTextField(clienteNuovo);
                    setBottoniCheCompaioQuandoModifico();
                }));
    }

    @Override
    public void setDatiOnTextField(Utente utente){
        Platform.runLater(()-> {
            nomeEcognome.setText(utente.getNome() + " " + utente.getCognome());
            textFieldNome.setText(utente.getNome());
            textFieldCognome.setText(utente.getCognome());
            textFieldEmail.setText(utente.getAccountAgente().getEmail());
            textFieldNumeroTelefono.setText(utente.getTelefono());
        });
    }

    @Override
    public void tornaIndietro() {
        try{
            DashBoardCliente.initializePageDashboardCliente(tornaIndietroBottone.getScene().getWindow(),utente);
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    @Override
    public void tornaIndietroPassword() {
        new Thread(()->{        UpdatePasswordService.updatePasswordCliente(getPasswordVecchia(), DashBoardClienteController.getCliente());
        }).start();
        Platform.runLater(()->{        paneAnnullaCambio.setVisible(false);
        });
    }

    @Override
    public void apriPaginaModificaPassword() {
        try{
            CambioPassword.initializePageCambioPassword(btnCancella.getScene().getWindow(),this,utente);
        } catch (IOException e){
            e.printStackTrace();
            AlertFactory.generateFailAlert("Errore apertura pagina!", "Siamo spiacenti si è verificato un errore nel caricamento della pagina cambio password");
        }
    }

}
