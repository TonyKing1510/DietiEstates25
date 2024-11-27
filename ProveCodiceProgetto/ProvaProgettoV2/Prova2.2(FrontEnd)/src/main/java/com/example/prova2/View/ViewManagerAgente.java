package com.example.prova2.View;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class ViewManagerAgente {

    private final Stage stage;

    // Costruttore per inizializzare lo stage
    public ViewManagerAgente(Stage stage) {
        this.stage = stage;
    }

    // Metodo per cambiare scena con effetto fade
    public void changeSceneWithFade(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent newSceneRoot = loader.load();

        // Crea una transizione Fade
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), stage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            stage.setScene(new Scene(newSceneRoot));
            stage.show();

            // Crea una transizione Fade in per la nuova scena
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), newSceneRoot);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }
}