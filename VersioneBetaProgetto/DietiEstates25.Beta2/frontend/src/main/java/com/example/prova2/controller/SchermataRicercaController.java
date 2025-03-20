package com.example.prova2.controller;

import com.example.prova2.dto.ImmobileResponseRicercaDTO;
import com.example.prova2.facade.ImmobileFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.factory.AnnunciFactory;
import com.example.prova2.model.*;
import com.example.prova2.service.ImageService;
import com.example.prova2.service.MappaService2;
import com.example.prova2.service.S3Service;
import com.example.prova2.view.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.prova2.factory.AnnunciFactory.createImageView;


public class SchermataRicercaController {
    @FXML
    private Button btnIndietro;
    @FXML
    private Hyperlink linkHome;
    @FXML
    private ScrollPane scrollFiltri;
    @FXML
    private WebView webViewMap;
    @FXML
    private ScrollPane scrollAnnunci;
    @FXML
    private MenuButton menuStato;
    @FXML
    private MenuButton menuClasse;
    @FXML
    private TextField textPrezzoMin;
    @FXML
    private TextField textPrezzoMax;
    @FXML
    private TextField textNumeroLocali;
    @FXML
    private TextField textLocalità;
    @FXML
    private Button btnAggiornaRicerca;
    @FXML
    private Label labelComuni;
    @FXML
    private Button btnUserDash;

    private static Utente utenteCheCerca;


    private final ExecutorService executor = Executors.newFixedThreadPool(10);


    public static void setUtenteCheCerca(Utente utenteCheCerca) {
        SchermataRicercaController.utenteCheCerca = utenteCheCerca;
    }

    public static Utente getUtenteCheCerca() {
        return utenteCheCerca;
    }

    private MappaService2 mappaService = new MappaService2();

    private WebEngine webEngine;

    private boolean isMapLoaded = false;

    public static void setPrezzoMin(BigDecimal prezzoMin) {
        SchermataRicercaController.prezzoMin = prezzoMin;
    }

    private static BigDecimal prezzoMin ;

    public static void setPrezzoMax(BigDecimal prezzoMax) {
        SchermataRicercaController.prezzoMax = prezzoMax;
    }

    private static BigDecimal prezzoMax;

    @FXML
    private void handleAvanti() {
        double currentHvalue = scrollFiltri.getHvalue();
        scrollFiltri.setHvalue(Math.min(1, currentHvalue + 0.2));
        setBtnIndietro();
    }

    @FXML
    private void handleIndietro() {
        double currentHvalue = scrollFiltri.getHvalue();
        scrollFiltri.setHvalue(Math.max(0, currentHvalue - 0.2));
        setBtnIndietro();
    }

    public void setBtnIndietro() {
        double currentHvalue = scrollFiltri.getHvalue();
        btnIndietro.setVisible(currentHvalue > 0.2);
    }

    @FXML
    public void initialize(String comune, TipoVendita tv, BigDecimal PrezzoMin, BigDecimal PrezzoMax, Integer numStanze, ClasseEnergetica ce) {
        Platform.runLater(() -> {
            webEngine = webViewMap.getEngine();
            comunicationJavaJavascript(webEngine);
            mappaService.setWebEngine(webEngine);  // Passa il WebEngine al servizio
            mappaService.loadMap();
            btnIndietro.setVisible(false);
            labelComuni.setText("Località: " + comune);
            checkPrezzoMinMax(PrezzoMin, PrezzoMax);
            checkTipoVendita(tv);
            checkClasseEnergetica(ce);
            checkNumStanze(numStanze);
            ArrayList<ClasseEnergetica> options3 = new ArrayList<>();
            ArrayList<TipoVendita> options4 = new ArrayList<>();
            aggiungiOpzioniArrayClasse(options3);
            aggiungiOpzioniArrayTipi(options4);
            addListenerClasseEn(options3);
            addListenerTipologia(options4);
            HomePageController.aggiungiListener(textPrezzoMax);
            HomePageController.aggiungiListener(textPrezzoMin);
        });
    }

    public void setTextIniziali(TipoVendita tv, BigDecimal PrezzoMin, BigDecimal PrezzoMax, Integer numStanze, ClasseEnergetica ce) {
        String tipoVendita = (tv != null) ? tv.toString() : "Stato Vendita";
        String prezzoMin = (PrezzoMin != null) ? PrezzoMin.toString() : "Inserire il prezzo minimo";
        String prezzoMax = (PrezzoMax != null) ? PrezzoMax.toString() : "Inserire il prezzo massimo";
        String numeroLocali = (numStanze != null) ? numStanze.toString() : "Numero locali";
        String classeEnergetica = (ce != null) ? ce.toString() : "Classe energetica";

        menuStato.setText(tipoVendita);
        menuClasse.setText(classeEnergetica);
        textPrezzoMin.setText(prezzoMin);
        textPrezzoMax.setText(prezzoMax);
        textNumeroLocali.setText(numeroLocali);
    }

    private void addListenerTipologia(ArrayList<TipoVendita> options) {
        for (TipoVendita option : options) {
            MenuItem menuItem = new MenuItem(option.toString()); // Imposta il testo visibile nel menu
            menuItem.setOnAction(event -> menuStato.setText(option.toString()));
            menuStato.getItems().add(menuItem);
        }
    }

    private void addListenerClasseEn(ArrayList<ClasseEnergetica> options) {
        for (ClasseEnergetica option : options) {
            MenuItem menuItem = new MenuItem(option.toString()); // Imposta il testo visibile nel menu
            menuItem.setOnAction(event -> menuClasse.setText(option.toString()));
            menuClasse.getItems().add(menuItem);
        }
    }

    private void aggiungiOpzioniArrayClasse(ArrayList<ClasseEnergetica> options) {
        menuClasse.getItems().clear();
        CreaAnnuncioController.setOptions(options);
    }

    private void aggiungiOpzioniArrayTipi(ArrayList<TipoVendita> options) {
        menuStato.getItems().clear();
        options.add(TipoVendita.VENDITA);
        options.add(TipoVendita.AFFITTO);
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


    public void loadAnnunci(String comune, TipoVendita tv, BigDecimal prezzoMin, BigDecimal prezzoMax, Integer numStanze, ClasseEnergetica ce) {
        Platform.runLater(() -> {
            if (!isMapLoaded) {
                webViewMap.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        caricaAnnunciAsync(comune, tv, prezzoMin, prezzoMax, numStanze, ce);
                    }
                });
            } else {
                caricaAnnunciAsync(comune, tv, prezzoMin, prezzoMax, numStanze, ce);
            }
        });
    }

    private void caricaAnnunciAsync(String comune, TipoVendita tv, BigDecimal prezzoMin, BigDecimal prezzoMax, Integer numStanze, ClasseEnergetica ce) {
        List<ImmobileResponseRicercaDTO> ricerca = ImmobileFacade.search(new Ricerca(ce, tv, comune, numStanze, prezzoMin, prezzoMax), utenteCheCerca);
        VBox vbox = getVBox();
        for (ImmobileResponseRicercaDTO ricercaDTO : ricerca) {
            Platform.runLater(() -> {
                try {
                    creaPane result = getCreaPane(ricercaDTO, vbox);
                    aggiornaMappa(ricercaDTO, result);
                    Task<List<String>> fetchImagesTask = recuperaImmagini(ricercaDTO, result);
                    Thread thread = new Thread(fetchImagesTask);
                    thread.setDaemon(true);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private Task<List<String>> recuperaImmagini(ImmobileResponseRicercaDTO ricercaDTO, creaPane result) {
        Task<List<String>> fetchImagesTask = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                return ImageService.getFotoOfImmobile(ricercaDTO.getIdImmobile()).join();
            }
        };
        fetchImagesTask.setOnSucceeded(event -> {
            List<String> images = fetchImagesTask.getValue();
            if (!images.isEmpty()) {
                String url = S3Service.getImageFromS3(images.get(0));
                System.out.println(url);
                aggiungiImmaginiAlPane(result.pane, url);
            }
        });
        fetchImagesTask.setOnFailed(event -> {
            Throwable ex = fetchImagesTask.getException();
            ex.printStackTrace();
        });
        return fetchImagesTask;
    }

    private void aggiungiImmaginiAlPane(Pane pane, String image) {
        if (pane != null) {
            System.out.println("hello");
            ImageView imageView = createImageView(image, 331, 413, 14, 14);
            pane.getChildren().addAll(imageView);
        }
    }


    private void aggiornaMappa(ImmobileResponseRicercaDTO ricercaDTO, creaPane result) {
        new Thread(() -> {
            String coordinates = MappaService2.getCoordinatesFromAddress(result.indirizzo());
            if (coordinates != null) {
                String[] dati = coordinates.split(",");
                double lat = Double.parseDouble(dati[0]);
                double lng = Double.parseDouble(dati[1]);
                String coordinatesComune = MappaService2.getCoordinatesFromAddress(result.comuneInd());
                if (coordinatesComune == null) {
                    return;
                }
                String[] datiComune = coordinatesComune.split(",");
                if (datiComune[0] == null || datiComune[1] == null) {
                    return;
                }
                double latComune = Double.parseDouble(datiComune[0]);
                double lngComune = Double.parseDouble(datiComune[1]);
                ricercaDTO.setLatitudine(lat);
                ricercaDTO.setLongitudine(lng);
                Platform.runLater(() -> mappaService.addMarker(latComune, lngComune, lat, lng, result.titolo(),
                        result.Prezzo(), result.descrizione(), String.valueOf(ricercaDTO.getIdImmobile())));
            }
        }).start();
    }

    private creaPane getCreaPane(ImmobileResponseRicercaDTO ricercaDTO, VBox vbox) {
        String Prezzo = String.valueOf(ricercaDTO.getPrezzo());
        String numBagn = String.valueOf(ricercaDTO.getNumeroBagni());
        String numSuper = String.valueOf(ricercaDTO.getSuperficie());
        String numPiano = String.valueOf(ricercaDTO.getPiano());
        String numLocali = String.valueOf(ricercaDTO.getNumeroStanze());
        String tipologia = String.valueOf(ricercaDTO.getTipoimmobile());
        String indirizzo = ricercaDTO.getVia() + "," + ricercaDTO.getNumeroCivico() + "," + ricercaDTO.getComune();
        String descrizione = ricercaDTO.getDescrizione();
        String titolo = ricercaDTO.getTitolo();
        String comuneInd = ricercaDTO.getComune();


        Pane pane = AnnunciFactory.createPaneAnnunci();
        Pane paneInfo = AnnunciFactory.createPaneInfo();
        paneInfo.setId("annunci_" + ricercaDTO.getIdImmobile());
        Pane panePrezzi = AnnunciFactory.createPaneInfoPrezzi(Prezzo);
        Pane paneForniture = AnnunciFactory.createPaneInfoForniture(numBagn, numLocali, numPiano, numSuper);
        Label labelTipo = AnnunciFactory.createLabelInfoTipo(tipologia);
        Label labelIndirizzo = AnnunciFactory.createLabelIndirizzo(indirizzo);
        Pane paneDescrizione = AnnunciFactory.createPaneInfoDescrizione(descrizione);
        Pane paneImmagini = AnnunciFactory.createPaneImmagini("");

        pane.getChildren().addAll(paneInfo, paneImmagini);
        paneInfo.getChildren().addAll(panePrezzi, paneForniture, labelTipo, labelIndirizzo, paneDescrizione);
        pane.setOnMouseClicked(e -> {
            SchermataAnnuncio sa = new SchermataAnnuncio();
            try {
                sa.initializeSchermataAnnuncio(pane,ricercaDTO,utenteCheCerca,prezzoMin,prezzoMax);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        vbox.getChildren().add(pane);
        scrollAnnunci.setContent(vbox);
        return new creaPane(Prezzo, indirizzo, descrizione, titolo, comuneInd, paneImmagini);
    }

    public record creaPane(String Prezzo, String indirizzo, String descrizione, String titolo, String comuneInd,
                           Pane pane) {
    }


    private static VBox getVBox() {
        VBox vbox = new VBox();
        vbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);// Usato per accumulare i prezzi
        return vbox;
    }


    public void handleSearch() throws IOException {
        String comune = textLocalità.getText();
        Integer numStanze = (textNumeroLocali.getText() == null || textNumeroLocali.getText().trim().isEmpty())
                ? null : Integer.parseInt(textNumeroLocali.getText());

        BigDecimal prezzoMin = (textPrezzoMin.getText() == null || textPrezzoMin.getText().trim().isEmpty())
                ? null : new BigDecimal(textPrezzoMin.getText().replace(".", ""));

        BigDecimal prezzoMax = (textPrezzoMax.getText() == null || textPrezzoMax.getText().trim().isEmpty())
                ? null : new BigDecimal(textPrezzoMax.getText().replace(".", ""));

        TipoVendita tipoVendita = (Objects.equals(menuStato.getText(), "Stato Vendita") || menuStato.getText().trim().isEmpty())
                ? null : TipoVendita.valueOf(menuStato.getText().trim());

        ClasseEnergetica classeEnergetica = (Objects.equals(menuClasse.getText(), "Classe energetica") || menuClasse.getText().trim().isEmpty())
                ? null : ClasseEnergetica.fromString(menuClasse.getText().trim());

        RicercaPage.caricamento((Stage) btnAggiornaRicerca.getScene().getWindow(), comune, tipoVendita, prezzoMin, prezzoMax, numStanze, classeEnergetica);
    }


    public void tornaHome() {
        linkHome.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                try {
                    HomePageController.setUtente(utenteCheCerca);
                    HomePage.initializeHomePage((Stage) linkHome.getScene().getWindow(), utenteCheCerca);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void checkTipoVendita(TipoVendita tipoVendita) {
        String tv = String.valueOf(tipoVendita);
        if (tv.equals("null")) {
            menuStato.setText("Stato Vendita");
        } else {
            menuStato.setText(String.valueOf(tipoVendita));
        }
    }

    private void checkPrezzoMinMax(BigDecimal prezzoMin, BigDecimal prezzoMax) {
        if (prezzoMin == null) {
            textPrezzoMin.setPromptText("Inserisci prezzo minimo");
        }else{
            textPrezzoMin.setText(String.valueOf(prezzoMin));
        }

        if (prezzoMax == null) {
            textPrezzoMax.setPromptText("Inserisci prezzo massimo");
        }else{
            textPrezzoMax.setText(String.valueOf(prezzoMax));
        }
    }

    private void checkClasseEnergetica(ClasseEnergetica classeEnergetica) {
        String ce = String.valueOf(classeEnergetica);
        if (ce.equals("null")) {
            menuClasse.setText("Classe energetica");
        }else{
            menuClasse.setText(String.valueOf(classeEnergetica));
        }
    }

    private void checkNumStanze(Integer numStanze) {
        if (numStanze == null) {
            textNumeroLocali.setPromptText("Inserisci numero stanze");
        }else{
            textNumeroLocali.setText(String.valueOf(numStanze));
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



}
