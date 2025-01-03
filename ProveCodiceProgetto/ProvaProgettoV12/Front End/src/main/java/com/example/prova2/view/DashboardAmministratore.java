package com.example.prova2.view;
import com.example.prova2.controller.DashboardAmministratoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashboardAmministratore {
    private DashboardAmministratore(){}
    public static void initializePageDashboardAmministratore(Window window) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/dashboardAmministratore.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        DashboardAmministratoreController controller = loader.getController();
        controller.loadNotifications();
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.setFullScreen(true); // Mantieni schermo intero anche nella scena di login
        stage.setResizable(true); // Consenti ridimensionamento
        stage.show();
    }
}
