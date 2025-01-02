package com.example.prova2.controller;
import com.example.prova2.model.Admin;
import com.example.prova2.model.Notifica;
import com.example.prova2.requester.NotificationsRequester;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.utility.ButtonFactory;
import com.example.prova2.utility.LabelFactory;
import com.example.prova2.utility.PaneFactory;
import com.example.prova2.view.CreaAgenteImmobiliare;
import com.example.prova2.view.CreaAnnuncioAgente;
import com.example.prova2.view.CreaGestore;
import com.example.prova2.view.HomePage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class DashboardAmministratoreController {
    public Button btnCreaAgentiImmobiliari;
    @FXML
    private Button btnCreaGestore;
    @FXML
    private VBox vbox1;
    @FXML
    private Button creannuncio;
    @FXML
    private Button btnHome;

    private static Admin admin;


    public void loadNotifications() throws IOException, InterruptedException {
        if (admin != null) {
            List<Notifica> notificationsOfAdmin = NotificationsRequester.getNotificationsForAdmin(admin.getCf());
            if (!notificationsOfAdmin.isEmpty()) {
                for (Notifica notification : notificationsOfAdmin) {
                    Pane pane = PaneFactory.createPane();
                    Button button = ButtonFactory.createButton();
                    Label label = LabelFactory.createLabel(notification.getNomeNotifica());
                    pane.getChildren().add(label);
                    button.setGraphic(pane);
                    vbox1.getChildren().add(button);
                }
            }
        }
    }


    @FXML
    private void handleCreaAnnuncio() throws IOException {
        CreaAnnuncioAgente.initializePageCreaAnnuncio(creannuncio.getScene().getWindow());
    }


    @FXML
    private void handleTornaHome(ActionEvent event) throws IOException {
        HomePage.initializeHomePage((Stage) btnHome.getScene().getWindow());
    }


    @FXML
    public void apriPaginaCreaGestori() throws IOException {
        CreaGestore.initializePageCreaGestore(btnCreaGestore.getScene().getWindow());
    }


    @FXML
    public void apriCreaAgenti() {
        try {
            CreaAgenteImmobiliare.initializePageCreaAgente(btnCreaAgentiImmobiliari.getScene().getWindow());
        } catch (IOException e){
            e.printStackTrace();
            AlertFactory.generateFailAlert("Errore di navigazione",
                    "Si è verificato un problema durante il caricamento della dashboard.");
        }
    }

    public static void setAdmin(Admin admin) {
        DashboardAmministratoreController.admin = admin;
    }


    public static Admin getAdmin() {
        return admin;
    }
}
