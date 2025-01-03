package com.example.prova2.view;

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

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Percorso del video
        String videoPath = Objects.requireNonNull(getClass().getResource("/com/example/prova2/images/C.mp4")).toExternalForm();

        // Creazione del Media e MediaPlayer
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Adattare il video alla finestra
        mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        mediaView.setPreserveRatio(false); // Adatta senza mantenere proporzioni


        // Layout e scena per lo splash screen
        StackPane root = new StackPane(mediaView);
        Scene splashScene = new Scene(root, 1540, 790); // Dimensioni personalizzabili

        // Imposta la scena di splash screen
        primaryStage.setScene(splashScene);
        primaryStage.show();

        // Imposta la modalità full screen
        primaryStage.setScene(splashScene);
        primaryStage.setFullScreen(true); // Schermo intero
        primaryStage.setFullScreenExitHint("Premi ESC per uscire dalla modalità full screen");
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

    public void startMainApp(Stage primaryStage) throws IOException {
        // Carica la scena del login
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("/com/example/prova2/loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        // Imposta la scena del login
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setFullScreen(true); // Mantieni schermo intero anche nella scena di login
        primaryStage.setResizable(true); // Consenti ridimensionamento
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
