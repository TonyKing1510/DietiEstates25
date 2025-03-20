package com.example.prova2.controller;
import com.example.prova2.dto.ImmobileResponseRicercaDTO;
import com.example.prova2.facade.AgenteServiceFacade;
import com.example.prova2.facade.VisitaServiceFacade;
import com.example.prova2.factory.AlertFactory;

import com.example.prova2.model.Immobile;
import com.example.prova2.model.Utente;
import com.example.prova2.service.ImageService;
import com.example.prova2.service.MappaService2;
import com.example.prova2.service.S3Service;
import com.example.prova2.view.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


import static com.example.prova2.factory.AnnunciFactory.createImageView;

public class SchermataAnnuncioController {
    @FXML
    private ImageView imageAgente;
    @FXML
    private ScrollPane scrollImageAnnuncio;
    @FXML
    private Label labelTipoImmobile;
    @FXML
    private Label labelIndirizzo;
    @FXML
    private Label labelPrezzo;
    @FXML
    private Label labelInfoTitolo;
    @FXML
    private Label labelInfoDescrizione;
    @FXML
    private Label labelNumeroStanze;
    @FXML
    private Label labelNumeroBagni;
    @FXML
    private Label labelNumeroCucine;
    @FXML
    private Label labelNumeroSoggiorni;
    @FXML
    private Label labelNumeroPiano;
    @FXML
    private Label labelSuperficie;
    @FXML
    private Label labelArredamento;
    @FXML
    private Label labelClasseEnergetica;
    @FXML
    private Label labelSpeseCondominiali;
    @FXML
    private Label labelNomeAgente;
    @FXML
    private WebView webViewMap;
    @FXML
    private Button btnPrenotaVisita;
    @FXML
    private Button btnLasciaRecensione;
    @FXML
    private Button btnIndietro2;
    @FXML
    private Button btnIndietroImage;
    @FXML
    private Button btnUserDash;
    @FXML
    private Hyperlink linkHome;

    private static Utente utenteCheCerca;

    private WebEngine webEngine;

    private boolean isMapLoaded = false;

    private final MappaService2 mappaService = new MappaService2();

    private static ImmobileResponseRicercaDTO annuncio;
    private static BigDecimal prezzoMin;
    private static BigDecimal prezzoMax;


    public static void setUtenteCheCerca(Utente utenteCheCerca) {
        SchermataAnnuncioController.utenteCheCerca = utenteCheCerca;
    }

    public SchermataAnnuncioController() {
    }

    public static void setDatiRicerca(ImmobileResponseRicercaDTO annuncioDto,BigDecimal prezzoMin, BigDecimal prezzoMax) {
        SchermataAnnuncioController.annuncio = annuncioDto;
        SchermataAnnuncioController.prezzoMin = prezzoMin;
        SchermataAnnuncioController.prezzoMax = prezzoMax;

    }


    public void loadAnnuncio(ImmobileResponseRicercaDTO annuncioDto){
        Platform.runLater(() -> {
            caricaImmagini(annuncioDto);
            setLabelImmobile(annuncioDto);
            setOnActioPrenota(annuncioDto);
            setPhotoAgente(annuncioDto);
            loadMap(annuncioDto);
            btnIndietroImage.setDisable(false);

        });
    }

    private void loadMap(ImmobileResponseRicercaDTO annuncioDto){
        webEngine = webViewMap.getEngine();
        comunicationJavaJavascript(webEngine);
        mappaService.setWebEngine(webEngine);  // Passa il WebEngine al servizio
        mappaService.loadMap();
        addMarkerMap(annuncioDto);
    }

    @FXML
    private void handleAvanti() {
        System.out.println("AVANTI");
        double currentHvalue = scrollImageAnnuncio.getHvalue();
        scrollImageAnnuncio.setHvalue(Math.min(1, currentHvalue + 0.5));
        setBtnIndietro();
    }

    @FXML
    private void handleIndietro() {
        double currentHvalue = scrollImageAnnuncio.getHvalue();
        double newHvalue = Math.max(0, currentHvalue - 0.5);

        scrollImageAnnuncio.setHvalue(newHvalue);
    }

    public void setBtnIndietro() {
        double currentHvalue = scrollImageAnnuncio.getHvalue();

        // Il bottone "Indietro" sarà visibile solo se Hvalue è maggiore di 0.2
        btnIndietroImage.setVisible(currentHvalue > 0.1);
    }



    private void comunicationJavaJavascript(WebEngine webEngine) {
        Platform.runLater(() -> {
            webEngine.setUserDataDirectory(new File("user-data"));
            webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    window.setMember("java", this);
                    isMapLoaded = true;
                }
            });
        });
    }

    private void addMarkerMap(ImmobileResponseRicercaDTO annuncioDto){
        new Thread(() -> {
            String indirizzo = annuncioDto.getComune() + " " + annuncioDto.getVia() + " " + annuncioDto.getNumeroCivico();
            String coordinates = MappaService2.getCoordinatesFromAddress(indirizzo);
            if (coordinates != null) {
                String[] dati = coordinates.split(",");
                double lat = Double.parseDouble(dati[0]);
                double lng = Double.parseDouble(dati[1]);
                String coordinatesComune = MappaService2.getCoordinatesFromAddress(annuncioDto.getComune());
                if (coordinatesComune == null) {
                    return;
                }
                String[] datiComune = coordinatesComune.split(",");
                if (datiComune[0] == null || datiComune[1] == null) {
                    return;
                }
                double latComune = Double.parseDouble(datiComune[0]);
                double lngComune = Double.parseDouble(datiComune[1]);
                annuncioDto.setLatitudine(lat);
                annuncioDto.setLongitudine(lng);
                Platform.runLater(() -> mappaService.addMarker2(latComune, lngComune, lat, lng));
            }
        }).start();
    }

    private void setPhotoAgente(ImmobileResponseRicercaDTO annuncioDto) {
        String cfAgente = annuncioDto.getCfAgente();
        List<String> image = AgenteServiceFacade.getFotoAgente(cfAgente);
        String urlImage = S3Service.getImageFromS3(image.getFirst());
        if(urlImage != null){
            imageAgente.setImage(new Image(urlImage));

        }

    }

    private void setOnActioPrenota(ImmobileResponseRicercaDTO annuncioDto) {
        btnPrenotaVisita.setOnAction(event -> apriPaginaPrenotazioni(annuncioDto));
    }

    private void caricaImmagini(ImmobileResponseRicercaDTO annuncioDto){
        Task<List<String>> imagesTask = prendiImmagini(annuncioDto);
        Thread thread = new Thread(imagesTask);
        thread.setDaemon(true);
        thread.start();
    }


    private Task<List<String>> prendiImmagini(ImmobileResponseRicercaDTO annuncioDto) {
        Task<List<String>> fetchImagesTask = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                return ImageService.getFotoOfImmobile(annuncioDto.getIdImmobile()).join();
            }
        };

        fetchImagesTask.setOnSucceeded(event -> {
            List<String> images = fetchImagesTask.getValue();
            if (images != null && !images.isEmpty()) {
                double x = 0;
                HBox hBox = new HBox(50);
                hBox.setAlignment(Pos.CENTER);
                for (String imageName : images) {
                    String url = S3Service.getImageFromS3(imageName);
                    aggiungiImmaginiAlloScroll(hBox, scrollImageAnnuncio, url,x);
                    x += 0.50;                 }
            }
        });

        fetchImagesTask.setOnFailed(event -> {
            Throwable ex = fetchImagesTask.getException();
            ex.printStackTrace();
        });

        return fetchImagesTask;
    }

    private void aggiungiImmaginiAlloScroll(HBox hBox,ScrollPane scrollPane, String url,double x){
        if (scrollPane != null) {
            ImageView imageView = createImageView(url, 925, 400, x, 14);
            hBox.getChildren().add(imageView);
            scrollPane.setContent(hBox);
        }

    }

    private void setLabelImmobile(ImmobileResponseRicercaDTO annuncioDto) {
        setDatiLabel(annuncioDto);
    }

    private void setDatiLabel(ImmobileResponseRicercaDTO annuncioDto) {
        String tipoImmobile = String.valueOf(annuncioDto.getTipoimmobile()).toLowerCase();
        tipoImmobile = tipoImmobile.substring(0, 1).toUpperCase() + tipoImmobile.substring(1);
        String tipoArredamento = String.valueOf(annuncioDto.getArredamento()).toLowerCase();
        tipoArredamento = tipoArredamento.substring(0, 1).toUpperCase() + tipoArredamento.substring(1);

        labelTipoImmobile.setText(tipoImmobile);
        labelArredamento.setText(tipoArredamento);

        labelIndirizzo.setText(annuncioDto.getComune() + " " + annuncioDto.getVia() + " " + annuncioDto.getNumeroCivico());
        labelPrezzo.setText("€ " + annuncioDto.getPrezzo());
        labelInfoTitolo.setText(annuncioDto.getTitolo());
        labelInfoDescrizione.setText(annuncioDto.getDescrizione());
        labelNumeroStanze.setText(String.valueOf(annuncioDto.getNumeroStanze()));
        labelNumeroBagni.setText(String.valueOf(annuncioDto.getNumeroBagni()));
        labelNumeroCucine.setText(String.valueOf(annuncioDto.getNumeroCucine()));
        labelNumeroSoggiorni.setText(String.valueOf(annuncioDto.getNumeroSoggiorni()));
        labelNumeroPiano.setText(String.valueOf(annuncioDto.getPiano()));
        labelSuperficie.setText(annuncioDto.getSuperficie() + " m²");

        labelClasseEnergetica.setText(String.valueOf(annuncioDto.getClasseEnergetica()));
        labelSpeseCondominiali.setText("€ " + annuncioDto.getSpeseCondominiali());
        labelNomeAgente.setText(annuncioDto.getNomeAgente() + " " + annuncioDto.getCognomeAgente());

    }


    private EventHandler<ActionEvent> apriPaginaPrenotazioni(ImmobileResponseRicercaDTO ricercaDTO) {
        try {
            if (!visitaGiaPrenotata(ricercaDTO)) {
                AlertVisitaPrenotata page = new AlertVisitaPrenotata();
                page.initPage((Stage) btnPrenotaVisita.getScene().getWindow(), new Immobile(ricercaDTO));
            } else {
                EffettuaPrenotazione.initPage((Stage) btnPrenotaVisita.getScene().getWindow(),
                        utenteCheCerca.getAccountAgente().getEmail(), new Immobile(ricercaDTO));
            }
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
        return null;
    }

    private static boolean visitaGiaPrenotata(ImmobileResponseRicercaDTO ricercaDTO) {
        return VisitaServiceFacade.checkVisitaGiaPresente(utenteCheCerca.getAccountAgente().getEmail(), ricercaDTO.getIdImmobile(), utenteCheCerca.getToken());
    }

    @FXML
    public void setLinkHome() throws IOException {
        caricaHome(linkHome.getScene().getWindow(),utenteCheCerca);
    }

    private void caricaHome(Window window, Utente utente) throws IOException {
        try {
            HomePage.caricamentoHome((Stage) window, utente);
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }


    public void apriDash() {
        try {

            DashBoardCliente.initializePageDashboardCliente(btnUserDash.getScene().getWindow(), utenteCheCerca);
        } catch (InterruptedException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreInterno();
        }
    }

    public void tornaAgliAnnunci(){
        try{
            RicercaPage.caricamento((Stage) btnIndietro2.getScene().getWindow(), annuncio.getComune(), annuncio.getTipovendita(),prezzoMin,prezzoMax,annuncio.getNumeroStanze(),annuncio.getClasseEnergetica());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

}
