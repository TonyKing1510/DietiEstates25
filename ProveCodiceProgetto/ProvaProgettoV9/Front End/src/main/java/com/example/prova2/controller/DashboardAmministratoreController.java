package com.example.prova2.controller;

import com.example.prova2.model.Admin;
import com.example.prova2.model.Notifica;
import com.example.prova2.requester.NotificationsRequester;
import com.example.prova2.utility.ButtonFactory;
import com.example.prova2.utility.LabelFactory;
import com.example.prova2.utility.PaneFactory;
import com.example.prova2.view.CreaAnnuncioAgente;
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
    @FXML
    private VBox vbox1;
    @FXML
    private Button creannuncio;
    @FXML
    private Button btnHome;

    private Admin admin;


    public void loadNotifications() throws IOException, InterruptedException {
        Admin admin1 = LoginAmministratoreController.getAdminDashBoard();
        if (admin1 != null) {
            List<Notifica> notificationsOfAdmin = NotificationsRequester.getNotificationsForAdmin(admin1.getPartitaIva());
            if (notificationsOfAdmin != null) {
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

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public Admin getAdmin() {
        return admin;
    }
}
