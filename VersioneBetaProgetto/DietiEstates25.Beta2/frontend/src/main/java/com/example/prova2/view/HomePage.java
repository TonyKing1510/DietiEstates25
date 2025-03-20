package com.example.prova2.view;

import com.example.prova2.controller.HomePageController;
import com.example.prova2.model.Utente;
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

    public static void caricamentoHome(Stage primaryStage,Utente utente) throws IOException {
        // Percorso del video
        String videoPath = Objects.requireNonNull(
                HomePage.class.getResource("/com/example/prova2/images/Caricamento.mp4")
        ).toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        StackPane root = new StackPane(mediaView);
        Scene splashScene = new Scene(root, 1540, 790);
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Caricamento...");
        primaryStage.show();
        initializeHomePage(primaryStage,utente);  // Chiama il metodo per caricare la HomePage

    }
    public static void initializeHomePage(Stage primaryStage,Utente utente) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomePage.class.getResource("/com/example/prova2/views/shared/schermataHome.fxml"));
        Scene mainScene = new Scene(loader.load(), 1540, 790);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Home");
        HomePageController homePage = loader.getController();
        homePage.initialize(utente);
    }


}
