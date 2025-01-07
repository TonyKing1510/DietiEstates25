package com.example.prova2.controller;
import com.example.prova2.model.AccountAmministratore;
import com.example.prova2.model.Admin;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.model.Notifica;
import com.example.prova2.service.NotificationService;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.utility.ButtonFactory;
import com.example.prova2.utility.LabelFactory;
import com.example.prova2.utility.PaneFactory;
import com.example.prova2.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class DashboardAmministratoreController {
    @FXML
    public Hyperlink btnCreaAgentiImmobiliari;
    @FXML
    private Hyperlink btnCreaGestore;
    @FXML
    private VBox vbox1;
    @FXML
    private Button creannuncio;

    private static GestoreAgenziaImmobiliare gestore;

    private static final String STRERROR = "errore di navigazione";


    public void initialize(){
        btnCreaGestore.setVisible(gestore.isAdmin());
    }


    public void loadNotifications() throws IOException, InterruptedException {
        if (gestore != null) {
            List<Notifica> notificationsOfAdmin = prendiNotifiche();
            if (ciSonoNotifiche(notificationsOfAdmin)) {
                for (Notifica notification : notificationsOfAdmin) {
                    Pane pane = PaneFactory.createPane();
                    Button button = ButtonFactory.createButton();
                    ifIsBottoneCambioPasswordSetAction(notification, button);
                    Label label = LabelFactory.createLabel(notification.getDescrizioneNotifica());
                    pane.getChildren().add(label);
                    button.setGraphic(pane);
                    vbox1.getChildren().add(button);
                }
            }
        }
    }

    private static List<Notifica> prendiNotifiche() throws IOException, InterruptedException {
        return NotificationService.getNotificationsForAdmin(gestore.getCf());
    }

    private static boolean ciSonoNotifiche(List<Notifica> notificationsOfAdmin) {
        return !notificationsOfAdmin.isEmpty();
    }

    private void ifIsBottoneCambioPasswordSetAction(Notifica notification, Button button) {
        if(isNotificaCambioPassword(notification))
            button.setOnAction(event ->
                apriPaginaCambiaPassword()
        );
    }

    private static boolean isNotificaCambioPassword(Notifica notification) {
        return notification.getNomeNotifica().equals("Cambio della password");
    }


    private void apriPaginaCambiaPassword() {
        try{
            CambioPassword.initializePageCambioPassword(btnCreaGestore.getScene().getWindow());
        } catch (IOException e){
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti si è verificato un errore nel caricamento della pagina cambio password");
        }
    }


    @FXML
    private void handleTornaHome(ActionEvent event) throws IOException {
        try {
            HomePage.initializeHomePage((Stage) btnCreaGestore.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti, si è verificato un errore durante il caricamento della pagina iniziale");
        }
    }


    @FXML
    public void apriPaginaCreaGestori(){
        try {
            CreaGestore.initializePageCreaGestore(btnCreaGestore.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti, si è verificato un errore durante il caricamento della pagina creazione annunci");
        }
    }


    @FXML
    public void apriCreaAgenti() {
        try {
            CreaAgenteImmobiliare.initializePageCreaAgente(btnCreaAgentiImmobiliari.getScene().getWindow());
        } catch (IOException e){
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Si è verificato un problema durante il caricamento della dashboard.");
        }
    }

    public static void setAdmin(GestoreAgenziaImmobiliare admin) {
        DashboardAmministratoreController.gestore = admin;
    }


    public static GestoreAgenziaImmobiliare getAdmin() {
        if(!gestore.isAdmin()){
            return gestore;
        }else{
            AccountAmministratore a = new AccountAmministratore(gestore.getAccountGestore().getEmail());
            Admin admin = new Admin(gestore.getNome(),gestore.getCognome(),gestore.getAgenziaAppartenente().getNomeAgenzia(),gestore.getCf());
            admin.setAccountAmministratore(a);
            return admin;
        }
    }
}
