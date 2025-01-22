package com.example.prova2.view;
import com.example.prova2.controller.DashboardAmministratoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashboardAmministratore {
    private DashboardAmministratore(){}
    public static void initializePageDashboardAmministratore(Window window) throws IOException,InterruptedException {
        FXMLLoader loader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/views/admin/dashBoardAdminNuova (1).fxml"));
        Scene scene = new Scene(loader.load(), 1540, 900);
        DashboardAmministratoreController controller = loader.getController();
        controller.loadNotifications();
        controller.initialize();
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();
    }
}
