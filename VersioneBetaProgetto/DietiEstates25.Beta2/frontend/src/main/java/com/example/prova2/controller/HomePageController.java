package com.example.prova2.controller;

import com.example.prova2.dto.GetAllRicercheResponse;
import com.example.prova2.facade.ClienteServiceFacade;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.ClasseEnergetica;
import com.example.prova2.model.TipoVendita;
import com.example.prova2.model.Utente;
import com.example.prova2.service.ClienteService;
import com.example.prova2.view.DashBoardCliente;
import com.example.prova2.view.RicercaPage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomePageController {

    private static Utente utente;
    @FXML
    public HBox hBoxPerPane;

    public static void setUtente(Utente utente) {
        HomePageController.utente = utente;
    }


    public static Utente getUtente() {
        return utente;
    }

    @FXML
    public ImageView fotoProfilo;
    @FXML
    private ScrollPane scrollPaneHome;
    @FXML
    private ScrollPane principalScrollPane;

    @FXML
    private Button btnAvanti;

    @FXML
    private Label labelErrorComune;
    @FXML
    private Label labelErrorNumeroStanze;
    @FXML
    private Pane annunciPane;
    @FXML
    private Pane principalPane;
    @FXML
    private Pane ricerchePane;
    @FXML
    private Pane newsPane;
    @FXML
    private Button btnCerca;
    @FXML
    private TextField textComune;
    @FXML
    private TextField textPrezzoMin;
    @FXML
    private TextField textPrezzoMax;
    @FXML
    private TextField textNumStanze;
    @FXML
    private MenuButton menuClasse;
    @FXML
    private MenuButton menuTipologia;
    @FXML
    private Button btnUserDash;

    @FXML
    private void handleAvanti() {
        double currentHvalue = scrollPaneHome.getHvalue();
        scrollPaneHome.setHvalue(Math.min(1, currentHvalue + 0.2));
    }

    @FXML
    private void handleIndietro() {
        double currentHvalue = scrollPaneHome.getHvalue();
        scrollPaneHome.setHvalue(Math.max(0, currentHvalue - 0.2));
    }

    @FXML
    private void scrollToPane(ActionEvent event){
        double targetPosition = annunciPane.getLayoutY() / principalPane.getHeight();
        principalScrollPane.setVvalue(targetPosition);

    }

    @FXML
    private void scrollToPaneRicercheRecenti(ActionEvent event){
        System.out.println("ciao");
        double offset = 0.1;
        // Calcolare la posizione verticale di "myPane" rispetto al contenuto totale e impostare lo scroll
        double targetPosition = (ricerchePane.getLayoutY() / principalPane.getHeight())+offset;

        principalScrollPane.setVvalue(targetPosition);

    }

    @FXML
    private void scrollToPaneNews(ActionEvent event){
        double offset = 0.1;
        double targetPosition = (newsPane.getLayoutY() / principalPane.getHeight())+offset;
        principalScrollPane.setVvalue(targetPosition);
    }

    public void initialize(Utente utente) {
        ArrayList<ClasseEnergetica> options = new ArrayList<>();
        ArrayList<TipoVendita> options2 = new ArrayList<>();
        aggiungiOpzioniArrayClasse(options);
        aggiungiOpzioniArrayTipi(options2);
        addListenerClasseEn(options);
        addListenerTipologia(options2);
        setUtente(utente);
        new Thread(()->{List<GetAllRicercheResponse> ricerchePassate=ClienteServiceFacade.getAllRicerche(utente);
        makeRicerchePassate(ricerchePassate);}).start();
        aggiungiListenerAiPrezzi(textPrezzoMax);
        aggiungiListenerAiPrezzi(textPrezzoMin);
    }

    private void aggiungiListenerAiPrezzi(TextField textSpese) {
        aggiungiListener(textSpese);
    }

    static void aggiungiListener(TextField textSpese) {
        textSpese.textProperty().addListener((observable, oldValue, newValue) -> {
            String cleanText = newValue.replaceAll("[^\\d]", "");
            if (!cleanText.isEmpty()) {
                StringBuilder formattedText = new StringBuilder();
                int count = 0;
                for (int i = cleanText.length() - 1; i >= 0; i--) {
                    formattedText.append(cleanText.charAt(i));
                    count++;
                    if (count % 3 == 0 && i != 0) {
                        formattedText.append(".");
                    }
                }
                textSpese.setText(formattedText.reverse().toString());
            } else {
                textSpese.setText("");
            }
        });
    }

    private void makeRicerchePassate(List<GetAllRicercheResponse> ricerchePassate) {
        Platform.runLater(()->{
            for (GetAllRicercheResponse ricerca : ricerchePassate){
                Pane pane = createPane(ricerca.getComune(), ricerca.getTipoVendita(),
                        ricerca.getPrezzoMin(), ricerca.getPrezzoMax(), ricerca.getNumeroStanze(),
                        ricerca.getClasseEnergetica());
                hBoxPerPane.getChildren().add(pane);
            }
        });
    }

    private Pane createPane(String comune, TipoVendita stato, BigDecimal prezzoMin, BigDecimal prezzoMax, Integer numStanze,
                            ClasseEnergetica classeEnergetica) {
        Pane pane = new Pane();
        pane.setPrefSize(358, 478);
        pane.setStyle("-fx-background-color: #f3f6f4; -fx-border-color: #011638; -fx-background-radius: 45; -fx-border-radius: 45;");
        pane.setMinSize(358, 478);
        pane.setMaxSize(358, 478);

        Label title = new Label("Case-Appartamenti");
        title.setLayoutX(14);
        title.setLayoutY(14);
        title.setStyle("-fx-text-fill: #011638; -fx-font-size: 32px; -fx-font-weight: bold;");

        Label labelComune = new Label("Comune: " + (!comune.isEmpty() ? comune : "Qualsiasi"));
        labelComune.setLayoutX(15);
        labelComune.setLayoutY(55);
        labelComune.setStyle("-fx-text-fill: #011638; -fx-font-size: 20px;");


        Label labelStato = new Label("Stato: " + (stato != null ? stato : "Qualsiasi"));
        labelStato.setLayoutX(15);
        labelStato.setLayoutY(103);
        labelStato.setStyle("-fx-text-fill: #011638; -fx-font-size: 20px;");

        Label labelPrezzoMin = new Label("Prezzo Minimo: " + (prezzoMin != null ? prezzoMin : "Qualsiasi"));
        labelPrezzoMin.setLayoutX(15);
        labelPrezzoMin.setLayoutY(153);
        labelPrezzoMin.setStyle("-fx-text-fill: #011638; -fx-font-size: 20px;");

        Label labelPrezzoMax = new Label("Prezzo Massimo: " + (prezzoMax != null ? prezzoMax : "Qualsiasi"));
        labelPrezzoMax.setLayoutX(15);
        labelPrezzoMax.setLayoutY(205);
        labelPrezzoMax.setStyle("-fx-text-fill: #011638; -fx-font-size: 20px;");

        Label labelNumStanze = new Label("Numero stanze: " + (numStanze != null ? numStanze : "Qualsiasi"));
        labelNumStanze.setLayoutX(15);
        labelNumStanze.setLayoutY(256);
        labelNumStanze.setStyle("-fx-text-fill: #011638; -fx-font-size: 20px;");

        System.out.println(classeEnergetica);
        Label labelClasseEnergetica = new Label("Classe Energetica: " + (classeEnergetica != null ? classeEnergetica : "Qualsiasi"));
        labelClasseEnergetica.setLayoutX(15);
        labelClasseEnergetica.setLayoutY(308);
        labelClasseEnergetica.setStyle("-fx-text-fill: #011638; -fx-font-size: 20px;");

        Button btnCercaPassati = new Button("Avvia ricerca");
        btnCercaPassati.setLayoutX(92);
        btnCercaPassati.setLayoutY(386);
        btnCercaPassati.setPrefSize(153, 40);
        btnCercaPassati.setStyle(" -fx-cursor: hand; -fx-background-radius: 8; -fx-background-color: #011638; -fx-text-fill: white;");
        btnCercaPassati.setOnAction(e ->
                faiRicerca(comune, stato, prezzoMin, prezzoMax, numStanze, classeEnergetica, btnCercaPassati)
        );
        pane.getChildren().addAll(title, labelComune, labelStato, labelPrezzoMin, labelPrezzoMax, labelNumStanze, labelClasseEnergetica, btnCercaPassati);
        return pane;
    }

    private static void faiRicerca(String comune, TipoVendita stato, BigDecimal prezzoMin, BigDecimal prezzoMax, Integer numStanze, ClasseEnergetica classeEnergetica, Button btnCerca)  {
        try {
            SchermataRicercaController.setUtenteCheCerca(utente);
            RicercaPage.caricamento((Stage) btnCerca.getScene().getWindow(), comune, stato, prezzoMin, prezzoMax, numStanze, classeEnergetica);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void addListenerTipologia(ArrayList<TipoVendita> options) {
        for (TipoVendita option : options) {
            MenuItem menuItem = new MenuItem(option.toString()); // Imposta il testo visibile nel menu
            menuItem.setOnAction(event -> menuTipologia.setText(option.toString()));
            menuTipologia.getItems().add(menuItem);
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
        CreaAnnuncioController.setOptions(options);
    }

    private void aggiungiOpzioniArrayTipi(ArrayList<TipoVendita> options) {
        options.add(TipoVendita.VENDITA);
        options.add(TipoVendita.AFFITTO);
    }


    public void handleSearch() {
        String comune = textComune.getText();


        boolean stanzeValide = checkNumStanze();
        boolean comuneValide = checkComune();

        // Se entrambi sono validi, procedi con la ricerca
        if (stanzeValide && comuneValide) {
            Integer numStanze = (textNumStanze.getText().trim().isEmpty())
                    ? null : Integer.parseInt(textNumStanze.getText());

            BigDecimal prezzoMin = (textPrezzoMin.getText().trim().isEmpty())
                    ? null : new BigDecimal(textPrezzoMin.getText().replace(".", ""));

            BigDecimal prezzoMax = (textPrezzoMax.getText().trim().isEmpty())
                    ? null : new BigDecimal(textPrezzoMax.getText().replace(".", ""));

            TipoVendita tipoVendita = (Objects.equals(menuTipologia.getText(), "Qualsiasi") || menuTipologia.getText().trim().isEmpty())
                    ? null : TipoVendita.valueOf(menuTipologia.getText().trim());

            ClasseEnergetica classeEnergetica = (Objects.equals(menuClasse.getText(), "Qualsiasi") || menuClasse.getText().trim().isEmpty())
                    ? null : ClasseEnergetica.fromString(menuClasse.getText().trim());

            faiRicerca(comune, tipoVendita, prezzoMin, prezzoMax, numStanze, classeEnergetica, btnCerca);
        }
    }

    private boolean checkNumStanze() {
        String numStanzeText = textNumStanze.getText().trim();

        boolean isNumStanzeValid = numStanzeText.isEmpty() || (numStanzeText.matches("-?\\d+") && Integer.parseInt(numStanzeText) >= 0);

        labelErrorNumeroStanze.setVisible(!isNumStanzeValid && !numStanzeText.isEmpty());

        return isNumStanzeValid;
    }

    private boolean checkComune(){
        String comuneText = textComune.getText().trim();
        boolean isComuneValid = !comuneText.isEmpty();

        labelErrorComune.setVisible(!isComuneValid);

        return isComuneValid;

    }


    public void apriDash() {
        try {
            System.out.println(utente.getToken());
            DashBoardCliente.initializePageDashboardCliente(btnAvanti.getScene().getWindow(), utente);
        } catch (InterruptedException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreInterno();
        }
    }
}
