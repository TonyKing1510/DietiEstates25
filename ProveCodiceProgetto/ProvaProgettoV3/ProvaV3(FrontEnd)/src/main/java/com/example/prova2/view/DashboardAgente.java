package com.example.prova2.view;

import com.example.prova2.controller.NotificationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashboardAgente {

    // Costruttore per inizializzare lo stage
    public DashboardAgente(Window window) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(HomePageLogin.class.getResource("/com/example/prova2/dashBoardAgente.fxml"));
        Scene scene = new Scene(loader.load(), 1540, 790);
        NotificationController controller = loader.getController();
        controller.loadNotifications();
        Stage stage = (Stage) window;
        stage.setScene(scene);
        stage.show();

    }
}
