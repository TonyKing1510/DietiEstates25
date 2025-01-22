package com.example.prova2.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertFactory {

    private AlertFactory(){}
    public static Alert generateAlertSuccess(String scrittaBottone1, String scrittaBottone2,String title,String head,String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(contenuto);

        ButtonType customYesButton = new ButtonType(scrittaBottone1, ButtonBar.ButtonData.NO);
        ButtonType customNoButton = new ButtonType(scrittaBottone2, ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(customYesButton, customNoButton);

        return alert;
    }

    public static Alert generateFailAlert(String titolo,String messaggio){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(titolo);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(messaggio);
        errorAlert.showAndWait();
        return errorAlert;
    }

    public static void generateFailAlertForCredenzialiSbagliate(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Errore di autenticazione");
        errorAlert.setHeaderText("Credenziali non valide");
        errorAlert.setContentText(
                """
                Siamo spiacenti, le credenziali inserite non sono corrette.
                La invitiamo a controllare i dati e riprovare.
        
                Se il problema persiste, può contattare il supporto tecnico per ricevere assistenza.
                """
        );
        errorAlert.showAndWait();
    }

    public static void generateFailAlertForErroreCaricamentoPagina(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Errore durante il caricamento della pagina");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(
                """
                Ci scusiamo per l'inconveniente. Qualcosa è andato storto durante il caricamento della pagina.
                Ti invitiamo a verificare la tua connessione a Internet o a riprovare più tardi.
                
                Se il problema persiste, contatta il nostro supporto tecnico.
                Grazie della tua pazienza!
                """
        );
        errorAlert.showAndWait();
    }

    public static void generateFailAlertForPrimoAccesso(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Hai già effettuato il primo accesso!");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(
                """
                Siamo spiacenti, la sua richiesta non è andata a buon fine perchè già ha compilato questo form.
                
                Si prega di cliccare il tasto accedi in alto a destra e continuare da li.
                """
        );
        errorAlert.showAndWait();
    }

    public static void generateFailAlertForAccountNonTrovato(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Account non trovato!");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(
                """
                Siamo spiacenti, la sua richiesta non è andata a
                buon fine perchè non ha ricevuto
                
                delle credenziali da amministrazione!
                """
        );
        errorAlert.showAndWait();
    }

    public static void generateFailAlertForErroreConnessione(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Errore di Connessione");
        errorAlert.setHeaderText("Ops! Il server non è raggiungibile.");
        errorAlert.setContentText("Cosa puoi fare:\n" +
                "- Verifica la tua connessione a Internet.\n" +
                "- Assicurati che il server sia attivo.\n" +
                "- Prova a riavviare l'applicazione.\n\n" +
                "Se il problema persiste, contatta il supporto tecnico.");
        errorAlert.showAndWait();
    }

    public static void generateFailAlertForErroreInterno() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Errore Interno Nel Server!");
        errorAlert.setHeaderText("Si è verificato un problema imprevisto.");
        errorAlert.setContentText(
                "Cosa puoi fare:\n" +
                        "- Prova a ripetere l'operazione.\n" +
                        "- Riavvia l'applicazione.\n\n" +
                        "Se il problema persiste, ti invitiamo a contattare il supporto tecnico per ricevere assistenza."
        );
        errorAlert.showAndWait();
    }


    public static void generateFailAlertForNonFattoPrimoAccesso(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Errore non fatto primo accesso!");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(
                """
                       Si è verificato un errore.
                       Siamo spiacenti,non risulta che sia
                       stato effettuato il primo accesso.
                       Si prega di tornare alla pagina dedicata al primo accesso, 
                       inserire le credenziali fornite e completare l'autenticazione.
                """
        );
        errorAlert.showAndWait();
    }


}
