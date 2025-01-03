package com.example.prova2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePage {

    private HomePage() {}

    // Metodo per mostrare la schermata di caricamento
    public static void caricamentoHome(Stage primaryStage) throws IOException {
        // Percorso del video
        String videoPath = Objects.requireNonNull(
                HomePage.class.getResource("/com/example/prova2/images/Caricamento.mp4")
        ).toExternalForm();

        // Creazione del Media e MediaPlayer
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Layout e scena per lo splash screen
        StackPane root = new StackPane(mediaView);
        Scene splashScene = new Scene(root, 1540, 790);

        // Imposta la scena di splash screen
        primaryStage.setScene(splashScene);
        primaryStage.setFullScreen(true); // Mantieni schermo intero anche nella scena di login
        primaryStage.setResizable(true); // Consenti ridimensionamento
        primaryStage.setTitle("Caricamento...");
        primaryStage.show();

        // Avvia il video
        mediaPlayer.play();

        // Azione al termine del video
        mediaPlayer.setOnEndOfMedia(() -> {
            try {
                // Carica la schermata principale e sostituisci la scena
                initializeHomePage(primaryStage);  // Chiama il metodo per caricare la HomePage
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // Metodo per inizializzare la schermata principale
    public static void initializeHomePage(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomePage.class.getResource("/com/example/prova2/schermataHome.fxml"));
        Scene mainScene = new Scene(loader.load(), 1540, 790);

        // Configura lo stage per la schermata principale
        primaryStage.setScene(mainScene);
        primaryStage.setFullScreen(true); // Mantieni schermo intero anche nella scena di login
        primaryStage.setResizable(true); // Consenti ridimensionamento
        primaryStage.setTitle("Home");
    }
}
