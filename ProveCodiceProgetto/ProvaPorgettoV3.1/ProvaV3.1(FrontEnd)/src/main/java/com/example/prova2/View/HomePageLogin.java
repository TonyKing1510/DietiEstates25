package com.example.prova2.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageLogin extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Percorso del video
        String videoPath = Objects.requireNonNull(getClass().getResource("/com/example/prova2/images/C.mp4")).toExternalForm();

        // Creazione del Media e MediaPlayer
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Layout e scena per lo splash screen
        StackPane root = new StackPane(mediaView);
        Scene splashScene = new Scene(root, 1540, 790); // Dimensioni personalizzabili

        // Imposta la scena di splash screen
        primaryStage.setScene(splashScene);
        primaryStage.show();

        // Avvia il video
        mediaPlayer.play();

        // Chiudi la splash screen e avvia l'app principale al termine del video
        mediaPlayer.setOnEndOfMedia(() -> {
            try {
                // Carica la schermata principale e sostituisci la scena
                startMainApp(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void startMainApp(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePageLogin.class.getResource("/com/example/prova2/Prova2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1540, 790);
        stage.setTitle("Hello!"); // Imposta il titolo della finestra principale
        stage.setScene(scene);   // Imposta la scena principale
    }

    public static void main(String[] args) {
        launch();
    }
}