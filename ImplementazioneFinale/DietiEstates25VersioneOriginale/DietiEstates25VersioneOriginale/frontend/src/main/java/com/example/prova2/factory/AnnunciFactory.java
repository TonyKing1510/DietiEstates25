package com.example.prova2.factory;

import com.example.prova2.facade.ImmobileFacade;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public class AnnunciFactory {
    private static final String STILEPANE = "-fx-background-color: rgba(255, 255, 255, 0.2); " +
            "-fx-border-color: white; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 10 10 10 10; " +
            "-fx-background-radius: 10 10 10 10;";

    private static final String STILEPANEANNUNCI = "-fx-background-color : rgba(1, 22, 56, 0.2); -fx-cursor: hand";

    private static final String STILEFONT = "-fx-text-fill: white; " +
            "-fx-font-family: System; " +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 16";

    private static final String STILEFONTFORNITURE = "-fx-text-fill: white; " +
            "-fx-font-family: System; " +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 12";

    private static final String STILEFONTANNUNCIAGENTE = "-fx-text-fill: white; " +
            "-fx-font-family: System; " +
            "-fx-font-weight: normal;" +
            "-fx-font-size: 12";

    public static Pane createPaneAnnunci() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-radius: 10 10 10 10;" + STILEPANEANNUNCI);
        pane.setPrefSize(806, 466);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.rgb(0, 0, 0, 0.3)); // Ombra nera con trasparenza

        // Effetto hover: ingrandisce il pane e aggiunge ombra
        pane.setOnMouseEntered(event -> {
            pane.setScaleX(1.02);
            pane.setScaleY(1.02);
            pane.setEffect(shadow); // Applica l'ombra
        });

        pane.setOnMouseExited(event -> {
            pane.setScaleX(1.0);
            pane.setScaleY(1.0);
            pane.setEffect(null); // Rimuove l'ombra
        });

        return pane;
    }

    public static Pane createPaneInfo(){
        Pane pane = new Pane();
        pane.setPrefSize(398, 457);
        pane.setLayoutX(403);
        pane.setLayoutY(6);
        pane.setStyle("-fx-cursor: hand");
        return pane;

    }

    public static Pane createPaneImmagini(String urlFoto) {
        Pane pane = new Pane();
        pane.setPrefSize(360, 444);
        pane.setLayoutX(14);
        pane.setLayoutY(14);

        // Pulizia dell'URL e divisione
        String cleanUrl = urlFoto.replaceAll("[\\[\\]\"]", "").trim();
        String[] urlArray = cleanUrl.split(",");

        try {
            if (urlArray.length > 0) {
                setta1immagine(urlArray, pane);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pane;
    }

    private static void setta1immagine(String[] urlArray, Pane pane) {
        String url1 = urlArray[0].trim();
        if (url1.startsWith("http")) {
            ImageView imageView = createImageView(url1, 331, 413, 14, 14);
            if (imageView != null) {
                pane.getChildren().clear();  // Rimuove tutte le immagini precedenti
                pane.getChildren().add(imageView); // Aggiungi l'immagine senza muoverla
            }
        }
    }



    public static ImageView createImageView(String url, double width, double height, double x, double y) {
        try {
            Image image = new Image(url);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(false);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            return imageView;
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dell'immagine: " + url);
            e.printStackTrace();
            return null;
        }
    }


    public static Pane createPaneInfoPrezzi(String infoPrezzi) {
        Pane pane = new Pane();
        pane.setStyle(STILEPANE);
        pane.setPrefSize(373, 73);
        pane.setLayoutX(14);
        pane.setLayoutY(14);
        Label label = new Label(infoPrezzi);
        Label label2 = new Label("Prezzo");
        Label label3 = new Label("€");
        label.setStyle(STILEFONT);
        label2.setStyle(STILEFONT);
        label3.setStyle(STILEFONT);
        label.setLayoutX(33);
        label.setLayoutY(37);
        label.setPrefSize(269, 25);
        label2.setLayoutX(14);
        label2.setLayoutY(6);
        label3.setLayoutX(14);
        label3.setLayoutY(37);
        pane.getChildren().add(label);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        return pane;

    }

    public static Pane createPaneInfoForniture(String numBagn, String numLocali, String numPiano, String numSuperficie){
        Pane pane = new Pane();
        pane.setStyle(STILEPANE);
        pane.setPrefSize(373, 174);
        pane.setLayoutX(14);
        pane.setLayoutY(115);
        addNumFornitureLabel(numBagn, numLocali, numPiano, numSuperficie, pane);
        addLabelForniture(pane);
        addImageForniture(pane);
        return pane;
    }

    public static Label createLabelInfoTipo(String tipo){
        Label label = new Label(tipo);
        label.setStyle(STILEFONTFORNITURE);
        label.setLayoutX(14);
        label.setLayoutY(299);
        return label;
    }

    public static Label createLabelIndirizzo(String indirizzo){
        Label label2 = new Label(indirizzo);
        label2.setStyle(STILEFONTFORNITURE);
        label2.setPrefSize(274,44);
        label2.setWrapText(true);
        label2.setAlignment(Pos.TOP_LEFT);
        label2.setLayoutX(125);
        label2.setLayoutY(299);
        return label2;
    }

    public static Pane createPaneInfoDescrizione(String descrizione){
        Pane pane = new Pane();
        pane.setStyle(STILEPANE);
        pane.setPrefSize(373, 93);
        pane.setLayoutX(13);
        pane.setLayoutY(354);
        Label label = new Label(descrizione);
        label.setStyle(STILEFONTFORNITURE);
        label.setPrefSize(350,61);
        label.setAlignment(Pos.TOP_LEFT);
        label.setLayoutX(14);
        label.setLayoutY(30);
        label.setWrapText(true);

        Label label2 = new Label("Descrizione");
        label2.setStyle(STILEFONTFORNITURE);
        label2.setLayoutX(13);
        label2.setLayoutY(5);

        pane.getChildren().add(label);
        pane.getChildren().add(label2);
        return pane;

    }

    private static void addImageForniture(Pane pane) {
        ImageView imageBagni = new ImageView(new Image(Objects.requireNonNull(AnnunciFactory.class.getResourceAsStream("/com/example/prova2/images/icons8-bagno-90.png"))));
        imageBagni.setFitWidth(40);
        imageBagni.setFitHeight(40);
        imageBagni.setLayoutX(14);
        imageBagni.setLayoutY(45);

        ImageView imageLocali = new ImageView(new Image(Objects.requireNonNull(AnnunciFactory.class.getResourceAsStream("/com/example/prova2/images/icons8-piantina-80.png"))));
        imageLocali.setFitWidth(40);
        imageLocali.setFitHeight(40);
        imageLocali.setLayoutX(184);
        imageLocali.setLayoutY(48);

        ImageView imageSup = new ImageView(new Image(Objects.requireNonNull(AnnunciFactory.class.getResourceAsStream("/com/example/prova2/images/icons8-superficie-80.png"))));
        imageSup.setFitWidth(40);
        imageSup.setFitHeight(40);
        imageSup.setLayoutX(14);
        imageSup.setLayoutY(110);

        ImageView imagePiano = new ImageView(new Image(Objects.requireNonNull(AnnunciFactory.class.getResourceAsStream("/com/example/prova2/images/icons8-scale-80.png"))));
        imagePiano.setFitWidth(40);
        imagePiano.setFitHeight(40);
        imagePiano.setLayoutX(184);
        imagePiano.setLayoutY(110);

        pane.getChildren().add(imageBagni);
        pane.getChildren().add(imageLocali);
        pane.getChildren().add(imageSup);
        pane.getChildren().add(imagePiano);
    }

    private static void addNumFornitureLabel(String numBagn, String numLocali, String numPiano, String numSuperficie, Pane pane) {
        Label label = new Label(numBagn);
        Label label2 = new Label(numLocali);
        Label label3 = new Label(numPiano);
        Label label4 = new Label(numSuperficie);
        label.setStyle(STILEFONTFORNITURE);
        label2.setStyle(STILEFONTFORNITURE);
        label3.setStyle(STILEFONTFORNITURE);
        label4.setStyle(STILEFONTFORNITURE);
        label.setLayoutX(65);
        label.setLayoutY(53);
        label2.setLayoutX(235);
        label2.setLayoutY(53);
        label3.setLayoutX(235);
        label3.setLayoutY(119);
        label4.setLayoutX(65);
        label4.setLayoutY(119);
        pane.getChildren().add(label);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        pane.getChildren().add(label4);
    }

    private static void addLabelForniture(Pane pane) {
        Label labelForniture = new Label("Forniture ed altro");
        Label Bagni = new Label("bagni");
        Label locali = new Label("locali");
        Label superficie = new Label("m²");
        Label piano = new Label("piano");
        labelForniture.setStyle(STILEFONT);
        Bagni.setStyle(STILEFONTFORNITURE);
        locali.setStyle(STILEFONTFORNITURE);
        piano.setStyle(STILEFONTFORNITURE);
        superficie.setStyle(STILEFONTFORNITURE);
        labelForniture.setLayoutX(14);
        labelForniture.setLayoutY(6);
        Bagni.setLayoutX(96);
        Bagni.setLayoutY(53);
        locali.setLayoutX(271);
        locali.setLayoutY(53);
        superficie.setLayoutX(95);
        superficie.setLayoutY(119);
        piano.setLayoutX(271);
        piano.setLayoutY(119);
        pane.getChildren().add(labelForniture);
        pane.getChildren().add(Bagni);
        pane.getChildren().add(locali);
        pane.getChildren().add(superficie);
        pane.getChildren().add(piano);
    }


    public static Pane createImageViewForFoto(File file, List<String> files,List<File> absPath) {
        Image mainImage = new Image(file.getPath());
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setFitWidth(265);
        mainImageView.setFitHeight(276);
        mainImageView.setPreserveRatio(false);

        Image overlayImage =new Image(Objects.requireNonNull(AnnunciFactory.class.getResource("/com/example/prova2/images/close.png")).toExternalForm());
        ImageView overlayImageView = new ImageView(overlayImage);
        overlayImageView.setFitWidth(30);
        overlayImageView.setFitHeight(30);
        overlayImageView.setPreserveRatio(true);
        overlayImageView.setStyle("-fx-cursor: hand;");

        overlayImageView.setLayoutX(230); // Larghezza del contenitore - larghezza immagine - margine
        overlayImageView.setLayoutY(5); // Margine superiore
        overlayImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane parent = (Pane) overlayImageView.getParent();
                if (parent != null && parent.getParent() instanceof Pane) {
                    ((Pane) parent.getParent()).getChildren().remove(parent);
                    absPath.remove(file);
                    files.remove("images/"+file.getName());
                }
            }
        });

        // Aggiunta al contenitore
        Pane pane = new Pane(mainImageView, overlayImageView);
        pane.setPrefSize(265, 276);

        return pane; // Ritorna il Pane con entrambe le immagini
    }





    public static HBox createHboxAnnunciAgente(String indice,String titolo,Pane paneSuccesso, String citta,VBox vbox,int id){
        HBox hbox = HBoxFactory.createHboxAnnunci();
        Image image = new Image(Objects.requireNonNull(AnnunciFactory.class.getResource("/com/example/prova2/images/close.png")).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-cursor: hand");


        Pane pane = new Pane();
        pane.setPrefSize(171,42);
        Pane pane1 = new Pane();
        pane1.setPrefSize(175,42);
        Pane pane2 = new Pane();
        pane2.setPrefSize(194,42);
        Label label = new Label(indice+". "+titolo);
        label.setLayoutX(5);
        label.setStyle(STILEFONTANNUNCIAGENTE);
        Label label2 = new Label(citta);
        label2.setLayoutX(5);
        label2.setStyle(STILEFONTANNUNCIAGENTE);

        pane.getChildren().add(label);
        pane2.getChildren().add(label2);
        hbox.getChildren().add(pane);

        hbox.getChildren().add(pane1);
        hbox.getChildren().add(pane2);
        hbox.getChildren().add(button);

        button.setOnAction(event -> {
            Alert alert=AlertFactory.generateAlertForErroreConfermaImmobileEliminato();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CompletableFuture.runAsync(() ->
                        ImmobileFacade.deleteImmobile(id));
                paneSuccesso.setVisible(true);
                mostraPanePer3Secondi(paneSuccesso);
                vbox.getChildren().remove(hbox);
            }
        });

        return hbox;
    }

    private static void mostraPanePer3Secondi(Pane paneSuccesso) {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event1 -> paneSuccesso.setVisible(false));
        delay.play();
    }


    public static VBox vboxAnnunci(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-background-color: lightgray;"); // Aggiunge un colore di sfondo per verificare la visibilità

        return vBox;
    }

}
