package com.example.prova2.controller.dashBoard;
import com.example.prova2.controller.HomePageController;
import com.example.prova2.controller.modificaProfilo.ModificaProfiloAgenteController;
import com.example.prova2.controller.notifiche.VisualizzaNotificheAgenteController;
import com.example.prova2.dto.DatiDTO;
import com.example.prova2.dto.ImmobileResponseRicercaDTO;
import com.example.prova2.facade.AgenteServiceFacade;
import com.example.prova2.facade.VisualizzaNotificheFacade;
import com.example.prova2.factory.*;
import com.example.prova2.model.*;
import com.example.prova2.service.ImmobileService;
import com.example.prova2.service.S3Service;
import com.example.prova2.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DashboardAgenteController {
    @FXML
    public ImageView immagineProfiloAgente;

    @FXML
    public Button btnCreaAnnunci;

    @FXML
    public Button btnModificaIlProfilo;
    @FXML
    public Button btnNotifiche;

    @FXML
    public Button btnGestisciApp;

    @FXML
    public Button buttonLogout;

    @FXML
    private Label labelNome;
    @FXML
    private Label labelCognome;
    @FXML
    private Label labelMail;
    @FXML
    private Label labelNomeProf;
    @FXML
    private Label labelCognomeProf;
    @FXML
    private Label labelTel;
    @FXML
    private Label labelBioProf;

    @FXML
    private ScrollPane scrollAnnunci;

    private static Agente agente;

    public static void setAgenteNuovo(Agente agenteNuovo) {
        agente = agenteNuovo;
    }

    public static Agente getAgente() {
        return agente;
    }

    public static void setAgente(Agente agente) {
        DashboardAgenteController.agente = agente;
    }

    public void initialize() {
        new Thread(()->{        CompletableFuture.runAsync(() -> getDatiAgente(agente.getAccountAgente().getEmail(),agente.getToken()));
        }).start();

        new Thread(()->{        CompletableFuture.runAsync(this::getAnnunciForAgente);
        }).start();
    }


    private void fetchFoto() {
        List<String> urls = AgenteServiceFacade.getFotoAgente(agente.getCf());
        System.out.println(urls.get(0));
        if (agenteHaFoto(urls)) {
            String urlImmagine=S3Service.getImageFromS3(urls.getFirst());
            System.out.println(urlImmagine);
            setImmagineProfilo(urlImmagine);
        } else {
            setImmagineProfilo("C:/Users/Utente/Desktop/frontend/src/main/resources/com/example/prova2/images/user (5).png.jpg/");
        }
    }

    private static boolean agenteHaFoto(List<String> urls) {
        return !urls.isEmpty();
    }

    private void setImmagineProfilo(String s) {
        immagineProfiloAgente.setImage(new Image(s));
        agente.setFotoProfilo(s);
    }

    private void getDatiAgente(String email,String token) {
        CompletableFuture.supplyAsync(() ->
                        VisualizzaNotificheFacade.getAgenteByEmail(email,token))
                .thenAcceptAsync(datiAgente -> {
                    if (datiAgente != null) {
                        Platform.runLater(() -> {
                                extracted(datiAgente);
                            CompletableFuture.runAsync(this::fetchFoto);
                            });
                    }
                });
    }


    private void extracted(DatiDTO dto) {
        labelNome.setText(dto.getNome());
        labelCognome.setText(dto.getCognome());
        labelNomeProf.setText(dto.getNome());
        labelCognomeProf.setText(dto.getCognome());
        labelMail.setText(dto.getEmail());
        labelTel.setText(dto.getTelefono());
        labelBioProf.setText(dto.getBio());
        agente.setDto(dto);
    }


    public void apriPaginaCreaAnnunci() {
        try {
            CreaAnnuncioAgente.initializePageCreaAnnuncio((Stage) btnCreaAnnunci.getScene().getWindow(), String.valueOf(agente.getCf()));
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            e.printStackTrace();
        }
    }


    public void apriPaginaModificaProfilo() {
        try {
            ModificaProfiloAgenteController.setUtente(agente);
            ModificaProfiloAgente.initializePageModificaProfilo(btnModificaIlProfilo.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    public void apriPaginaVisualizzaNotifiche() {
        VisualizzaNotificheAgenteController.setAgente(agente);
        VisualizzaNotificheAgente.initPage((Stage) btnNotifiche.getScene().getWindow());
    }

    public void apriCalendario() {
        CalendarioAgente.initCalendario((Stage) btnGestisciApp.getScene().getWindow());
    }


    public void faiLogout() {
        agente = null;
        vaiIndietro();
    }

    public void vaiIndietro() {
        try {
            LoginAgenteImmobiliare.initializePageLoginAgente(buttonLogout.getScene().getWindow());
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    public void apriPaginaCercaImmobili() {
        try {
            HomePageController.setUtente(DashboardAgenteController.getAgente());
            HomePage.initializeHomePage((Stage) buttonLogout.getScene().getWindow(),agente);
        } catch (IOException e) {
            AlertFactory.generateFailAlertForErroreCaricamentoPagina();
        }
    }

    private List<ImmobileResponseRicercaDTO> prendiAnnunci() throws IOException, InterruptedException {
        return ImmobileService.getAnnunciForAgente(agente.getAccountAgente().getEmail(),agente.getToken());
    }


    public void getAnnunciForAgente() {
        CompletableFuture.runAsync(() -> {
            try {
                List<ImmobileResponseRicercaDTO> immobili = prendiAnnunci();
                VBox vBox = new VBox();
                vBox.setSpacing(10);
                vBox.setPadding(new Insets(10));
                vBox.setFillWidth(true);

                int i = 1;
                for (ImmobileResponseRicercaDTO annunci : immobili) {
                    String titolo = annunci.getTitolo();
                    String tipologia = String.valueOf(annunci.getTipoimmobile());
                    String indirizzo = "Comune di: " + annunci.getComune();
                    HBox hbox = AnnunciFactory.createHboxAnnunciAgente(String.valueOf(i), titolo, tipologia, indirizzo);
                    vBox.getChildren().add(hbox);
                    i++;
                }
                Platform.runLater(() -> scrollAnnunci.setContent(vBox));

            } catch (IOException e){
                e.printStackTrace();
                AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                AlertFactory.generateFailAlertForErroreCaricamentoPagina();
            }
        });
    }
}
