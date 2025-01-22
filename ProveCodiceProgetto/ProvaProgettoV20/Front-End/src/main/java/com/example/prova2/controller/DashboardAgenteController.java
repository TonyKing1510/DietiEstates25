package com.example.prova2.controller;
import com.example.prova2.dto.AgenteDatiDTO;
import com.example.prova2.facade.AgenteServiceFacade;
import com.example.prova2.facade.NotificaServiceFacade;
import com.example.prova2.factory.*;
import com.example.prova2.model.AccountAziendale;
import com.example.prova2.model.Agente;
import com.example.prova2.model.Notifica;
import com.example.prova2.service.NotificaService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class DashboardAgenteController {
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
    private Label labelBioProf;

    @FXML
    private Pane vbox2;

    @FXML
    private VBox vboxAnnunci;

    private static Agente agente;

    public static void setAgente(AccountAziendale aziendale){
        agente = new Agente(aziendale);
    }

    public void initialize(){
        getDatiAgente(agente.getAccountAgente().getEmail());
    }


    public void loadNotifications()  {
        if (agente.getAccountAgente().getEmail() != null) {
            List<Notifica> notificationsOfAgente = prendiNotifiche();
            if (ciSonoNotifiche(notificationsOfAgente)) {

                List<TextArea> textAreaList = new ArrayList<>();

                int index = 1;  // Variabile per tenere traccia dell'indice

                for (Notifica notification : notificationsOfAgente) {
                    AnchorPane anchorPane = new AnchorPane();
                    Button button = ButtonFactory.createButton();


                    TextArea textArea = TextAreaFactory.createTextArea(notification.getDescrizioneNotifica());
                    textArea.setVisible(false);
                    textAreaList.add(textArea);


                    String labelText = index + ". " + notification.getNomeNotifica();
                    Label label = LabelFactory.createLabelBianco(labelText);
                    label.setWrapText(false); // Abilita il wrapping del testo per evitare il taglio

                    // Aggiungi l'etichetta al Pane
                    anchorPane.getChildren().add(label);


                    Button buttonLetta = ButtonFactory.createLettaImageButton();
                    Button buttonRifiuta = ButtonFactory.createRifiutaImageButton();


                    addActionOnPulsanteLeggi(notification, buttonLetta, textArea);


                    addActionOnPulsanteRifiuta(notification, buttonRifiuta, button, textArea);

                    // Creiamo un HBox per i pulsanti Letta e Rifiuta
                    HBox buttonBox = new HBox(10, buttonLetta, buttonRifiuta); // Distanza di 10 tra i pulsanti
                    buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

                    // Crea un HBox per etichetta e pulsanti
                    HBox hBoxContainer = new HBox(20, label, buttonBox); // Spazio tra etichetta e pulsanti
                    hBoxContainer.setAlignment(Pos.CENTER_LEFT); // Allinea tutto a sinistra
                    hBoxContainer.setPadding(new Insets(10, 0, 10, 0)); // Margini interni// Posiziona a destra in basso

                    // Aggiungi l'HBox (contenente i pulsanti) all'AnchorPane
                    anchorPane.getChildren().add(buttonBox);

                    // Imposta le ancore per posizionare correttamente i pulsanti nell'AnchorPane
                    AnchorPane.setBottomAnchor(buttonBox, 10.0);  // Distanza dal basso
                    AnchorPane.setRightAnchor(buttonBox, 10.0);   // Distanza da destra

                    // Imposta l'azione del bottone per mostrare il TextArea
                    addActionForViewTextArea(button, textAreaList, textArea);

                    // Aggiungi il contenuto all'AnchorPane
                    anchorPane.getChildren().add(hBoxContainer);
                    button.setGraphic(anchorPane);
                    vbox1.getChildren().add(button);
                    vbox2.getChildren().add(textArea);  // Aggiungi il TextArea alla VBox

                    // Incrementa l'indice per la prossima notifica
                    index++;
                }
            }
        }
    }

    private static void addActionForViewTextArea(Button button, List<TextArea> textAreaList, TextArea textArea) {
        button.setOnAction(e -> {
            // Nascondi tutti i TextArea
            for (TextArea ta : textAreaList) {
                ta.setVisible(false);
            }
            // Mostra il TextArea associato alla notifica cliccata
            textArea.setVisible(true);
        });
    }

    private void addActionOnPulsanteRifiuta(Notifica notification, Button buttonRifiuta, Button button, TextArea textArea) {
        buttonRifiuta.setOnAction(e -> {
            // Rimuovi la notifica e il relativo TextArea
            vbox1.getChildren().remove(button);  // Rimuovi il bottone
            vbox2.getChildren().remove(textArea); // Rimuovi il TextArea
            NotificaService.setNotificationRejected(notification.getIdNotifica());  // Rimuovi dalla lista (opzionale)
        });
    }

    private static void addActionOnPulsanteLeggi(Notifica notification, Button buttonLetta, TextArea textArea) {
        buttonLetta.setOnAction(e -> {
            textArea.setText("Notifica letta: " + notification.getDescrizioneNotifica());
            textArea.setVisible(true);// Mostra il TextArea quando "Letta" è cliccato
            NotificaService.setNotificationAccepted(notification.getIdNotifica());

        });
    }

    private List<Notifica> prendiNotifiche() {
        return NotificaServiceFacade.getNotificaAgente(agente.getCf());
    }

    private static boolean ciSonoNotifiche(List<Notifica> notificationsOfAgente) {
        return !notificationsOfAgente.isEmpty();
    }


    private void getDatiAgente(String email) {
        AgenteDatiDTO datiAgente = AgenteServiceFacade.getAgenteByEmail(email);
        if (datiAgente != null) {
            extracted(datiAgente);
        }
    }

    private void extracted(AgenteDatiDTO dto) {
        labelNome.setText(dto.getNome());
        labelCognome.setText(dto.getCognome());
        labelNomeProf.setText(dto.getNome());
        labelCognomeProf.setText(dto.getCognome());
        labelMail.setText(dto.getEmail());
        labelTel.setText(dto.getTelefono());
        labelPartitaIva.setText(dto.getPartitaIva());
        labelBioProf.setText(dto.getBio());
        agente.setDto(dto);
    }




}
