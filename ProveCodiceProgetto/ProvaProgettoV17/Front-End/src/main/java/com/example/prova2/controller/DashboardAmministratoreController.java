package com.example.prova2.controller;
import com.example.prova2.model.AccountAmministratore;
import com.example.prova2.model.Admin;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.model.Notifica;
import com.example.prova2.service.GetGestoreService;
import com.example.prova2.service.NotificationService;
import com.example.prova2.utility.*;
import com.example.prova2.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardAmministratoreController {
    @FXML
    public Button btnCreaAgentiImmobiliari;
    @FXML
    private Button btnCreaGestore;
    @FXML
    private VBox vbox1;
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
    private Label labelPartitaIva;
    @FXML
    private HBox hbox1;
    @FXML
    private Pane vbox2;

    @FXML
    private Button buttonLogout;



    private static GestoreAgenziaImmobiliare gestore;

    private static final String STRERROR = "errore di navigazione";


    public void initialize(){
        if (gestore != null) {
            btnCreaGestore.setVisible(gestore.isAdmin());
            String username = gestore.getAccountGestore().getUsername();
            getGestoreData(username);
        } else {
            AlertFactory.generateFailAlert("Errore!", "Dati del gestore non disponibili!");
        }
    }


    public void loadNotifications() throws IOException, InterruptedException {
        if (gestore != null) {
            List<Notifica> notificationsOfAdmin = prendiNotifiche();
            if (ciSonoNotifiche(notificationsOfAdmin)) {
                // Crea una lista per tenere traccia dei TextArea
                List<TextArea> textAreaList = new ArrayList<>();

                int index = 1;  // Variabile per tenere traccia dell'indice

                for (Notifica notification : notificationsOfAdmin) {
                    // Crea un pannello per la notifica (uso AnchorPane per il posizionamento preciso)
                    AnchorPane anchorPane = new AnchorPane();
                    Button button = ButtonFactory.createButton();

                    // Usa la TextAreaFactory per creare un TextArea associato alla notifica
                    TextArea textArea = TextAreaFactory.createTextArea(notification.getDescrizioneNotifica());
                    textArea.setVisible(false); // Inizialmente non visibile
                    // Aggiungi il TextArea alla lista
                    textAreaList.add(textArea);

                    // Crea l'etichetta per la descrizione della notifica
                    String labelText = index + ". " + notification.getNomeNotifica();
                    Label label = LabelFactory.createLabel(labelText);
                    label.setWrapText(true); // Abilita il wrapping del testo per evitare il taglio

                    // Aggiungi l'etichetta al Pane
                    anchorPane.getChildren().add(label);

                    // Crea i pulsanti "Letta" e "Rifiuta"
                    Button buttonLetta = ButtonFactory.createLettaImageButton();
                    Button buttonRifiuta = ButtonFactory.createRifiutaImageButton();

                    // Azione del pulsante "Letta" (segna la notifica come letta)
                    buttonLetta.setOnAction(e -> {
                        textArea.setText("Notifica letta: " + notification.getDescrizioneNotifica());
                        textArea.setVisible(true);// Mostra il TextArea quando "Letta" è cliccato

                        System.out.println(notification.getIdNotifica());
                        NotificationService.setNotificationAccepted(notification.getIdNotifica());

                    });

                    // Azione del pulsante "Rifiuta" (elimina la notifica)
                    buttonRifiuta.setOnAction(e -> {
                        // Rimuovi la notifica e il relativo TextArea
                        vbox1.getChildren().remove(button);  // Rimuovi il bottone
                        vbox2.getChildren().remove(textArea); // Rimuovi il TextArea
                        NotificationService.setNotificationRejected(notification.getIdNotifica());  // Rimuovi dalla lista (opzionale)
                    });

                    // Creiamo un HBox per i pulsanti Letta e Rifiuta
                    HBox buttonBox = new HBox(10, buttonLetta, buttonRifiuta); // Distanza di 10 tra i pulsanti
                    buttonBox.setAlignment(Pos.BOTTOM_RIGHT);  // Posiziona a destra in basso

                    // Aggiungi l'HBox (contenente i pulsanti) all'AnchorPane
                    anchorPane.getChildren().add(buttonBox);

                    // Imposta le ancore per posizionare correttamente i pulsanti nell'AnchorPane
                    AnchorPane.setBottomAnchor(buttonBox, 10.0);  // Distanza dal basso
                    AnchorPane.setRightAnchor(buttonBox, 10.0);   // Distanza da destra

                    // Imposta l'azione del bottone per mostrare il TextArea
                    button.setOnAction(e -> {
                        // Nascondi tutti i TextArea
                        for (TextArea ta : textAreaList) {
                            ta.setVisible(false);
                        }
                        // Mostra il TextArea associato alla notifica cliccata
                        textArea.setVisible(true);
                    });

                    // Aggiungi il bottone al pannello e aggiungi il pannello alla VBox
                    button.setGraphic(anchorPane);
                    vbox1.getChildren().add(button);
                    vbox2.getChildren().add(textArea);  // Aggiungi il TextArea alla VBox

                    // Incrementa l'indice per la prossima notifica
                    index++;
                }
            }
        }
    }

    private static List<Notifica> prendiNotifiche() throws IOException, InterruptedException {
        return NotificationService.getNotificationsForAdmin(gestore.getCf());
    }

    private static boolean ciSonoNotifiche(List<Notifica> notificationsOfAdmin) {
        return !notificationsOfAdmin.isEmpty();
    }

    private void ifIsBottoneCambioPasswordSetAction(Notifica notification, Button button) {
        if(isNotificaCambioPassword(notification))
            button.setOnAction(event ->
                    apriPaginaCambiaPassword()
            );
    }

    private static boolean isNotificaCambioPassword(Notifica notification) {
        return notification.getNomeNotifica().equals("Cambio della password");
    }


    private void apriPaginaCambiaPassword() {
        try{
            CambioPassword.initializePageCambioPassword(btnCreaGestore.getScene().getWindow());
        } catch (IOException e){
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti si è verificato un errore nel caricamento della pagina cambio password");
        }
    }


    @FXML
    private void handleTornaHome(ActionEvent event) throws IOException {
        try {
            HomePage.initializeHomePage((Stage) btnCreaGestore.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti, si è verificato un errore durante il caricamento della pagina iniziale");
        }
    }


    @FXML
    public void apriPaginaCreaGestori(){
        try {
            CreaGestore.initializePageCreaGestore(btnCreaGestore.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti, si è verificato un errore durante il caricamento della pagina creazione annunci");
        }
    }


    @FXML
    public void apriCreaAgenti() {
        try {
            CreaAgenteImmobiliare.initializePageCreaAgente(btnCreaAgentiImmobiliari.getScene().getWindow());
        } catch (IOException e){
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Si è verificato un problema durante il caricamento della dashboard.");
        }
    }

    public static void setAdmin(GestoreAgenziaImmobiliare admin) {
        DashboardAmministratoreController.gestore = admin;
    }


    public static GestoreAgenziaImmobiliare getAdmin() {
        if(!gestore.isAdmin()){
            return gestore;
        }else{
            AccountAmministratore a = new AccountAmministratore(gestore.getAccountGestore().getEmail());
            Admin admin = new Admin(gestore.getNome(),gestore.getCognome(),gestore.getAgenziaAppartenente().getNomeAgenzia(),gestore.getCf());
            admin.setAccountAmministratore(a);
            return admin;
        }
    }

    // Metodo per ottenere i dati del gestore
    public void getGestoreData(String username) {
        try {
            // Chiamata al servizio per ottenere la mappa dei dati
            Map<String, Object> gestoreData = GetGestoreService.getGestori(username);
            if (gestoreData != null && !gestoreData.isEmpty()) {
                // Recupera i singoli valori dalla mappa
                String nome = (String) gestoreData.get("nome");
                String cognome = (String) gestoreData.get("cognome");
                String telefono = (String) gestoreData.get("telefono");
                String email = (String) gestoreData.get("email");
                String partitaiva = (String) gestoreData.get("partitaiva");
                extracted(nome, cognome, email, telefono, partitaiva);
            } else {
                System.out.println("Nessun dato trovato per l'username " + username);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Gestisci gli errori in modo appropriato
        }
    }

    private void extracted(String nome, String cognome, String email, String telefono, String partitaiva) {
        labelNome.setText(nome);
        labelCognome.setText(cognome);
        labelNomeProf.setText(nome);
        labelCognomeProf.setText(cognome);
        labelMail.setText(email);
        labelTel.setText(telefono);
        labelPartitaIva.setText(partitaiva);
    }

    @FXML
    public void faiLogout(ActionEvent event) throws IOException {
        try {
            LoginPage.startMainApp((Stage) buttonLogout.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert(STRERROR,
                    "Siamo spiacenti, si è verificato un errore durante il caricamento della pagina creazione annunci");
        }
    }

}