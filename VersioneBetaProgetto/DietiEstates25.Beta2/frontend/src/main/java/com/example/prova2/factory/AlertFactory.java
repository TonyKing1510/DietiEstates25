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
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().setMinHeight(170);
        return alert;
    }

    public static void generateSuccessAlertForSuccessUpdateDati() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Complimenti!");
        alert.setHeaderText("Hai aggiornato i tuoi dati del profilo!");
        alert.setContentText(
                """
                I tuoi dati del profilo sono stati aggiornati. Potrebbe volerci
                qualche minuto prima che i cambiamenti siano visibili
                nella tua area riservata. Grazie per la pazienza!
                """
        );
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    public static Alert generateSuccessAlertForSuccessInvioPrenotazione() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Complimenti!");
        alert.setHeaderText("Hai inviato una richiesta di prenotazione");
        alert.setContentText(
                """
                La Sua richiesta di prenotazione per l'immobile è stata inviata con successo.Le consigliamo di controllare regolarmente la 
                Sua casella delle notifiche per ricevere aggiornamenti e per 
                essere informato quando l'agente risponderà.
                """
        );
        alert.getButtonTypes().setAll(ButtonType.OK);
        return alert;
    }

    public static void generateSuccessAlertForSuccessUpdateBio() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Complimenti!");
        alert.setHeaderText("Hai aggiornato la tua biografia!");
        alert.setContentText(
                """
                La tua biografia è stata aggiornata. Potrebbe volerci
                qualche minuto prima che i cambiamenti siano visibili
                nella tua area riservata. Grazie per la pazienza!
                """
        );
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
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

    public static Alert generateAlertForAggiornaCategoria(){
        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
        errorAlert.setTitle("Complimenti!");
        errorAlert.setHeaderText("La tua richiesta è andata a buon fine!");
        errorAlert.setContentText(
                """
               Le tue preferenze sono state aggiornate con successo!
               Puoi modificarle in qualsiasi momento tornando 
               su questa pagina.
                """
        );
        errorAlert.getButtonTypes().setAll(ButtonType.OK);
        errorAlert.showAndWait();
        return errorAlert;
    }

    public static Alert generateAlertForAccettaNotifica(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Stai accettando la visita");
        errorAlert.setHeaderText("Conferma accettazione della visita!");
        errorAlert.setContentText(
                """
               Accettando la visita, l'utente che ha richiesto la visita riceverà
               una notifica per la conferma della visita.
                """
        );
        errorAlert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
        return errorAlert;
    }

    public static Alert generateAlertForRifiutaNotifica(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Stai rifiutando la visita");
        errorAlert.setHeaderText("Conferma rifiuto della visita!");
        errorAlert.setContentText(
                """
               Rifiutando la visita, l'utente che ha richiesto la visita riceverà una
               notifica per selezionare un nuovo orario per programmare 
               un'altra visita dell'immobile.
                """
        );
        errorAlert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
        return errorAlert;
    }

    public static Alert generateAlertForSuccessAccettaNotifica(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Hai accettato la visita!");
        errorAlert.setHeaderText("Complimenti");
        errorAlert.setContentText(
                """
               Complimenti, la visita è stata accettata con successo.
               Puoi visualizzare le visite accettate
               nella sezione "Gestione Appuntamenti" 
               tramite un calendario apposito.
                """
        );
        errorAlert.getButtonTypes().setAll(ButtonType.OK);
        return errorAlert;
    }

    public static Alert generateAlertForSuccessRifiutaNotifica(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Hai rifiutato la visita!");
        errorAlert.setHeaderText("Complimenti");
        errorAlert.setContentText(
                """
               Complimenti,la visita è stata rifiutata con successo.
               """
        );
        errorAlert.getButtonTypes().setAll(ButtonType.OK);
        return errorAlert;
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




}
