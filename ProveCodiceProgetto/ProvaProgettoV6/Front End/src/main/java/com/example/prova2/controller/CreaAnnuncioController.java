package com.example.prova2.controller;

import com.example.prova2.requester.ImageRequester;
import com.example.prova2.requester.RicercaComuniRequester;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreaAnnuncioController {
    @FXML
    private Button btnFoto;
    @FXML
    private TextField textComune;
    @FXML
    private ListView<String> ListMaps;

   public void uploadImage() throws IOException, InterruptedException {
       System.out.println("uploadImage chiamato");

       // Ottieni la finestra corrente dal pulsante
       Window currentWindow = btnFoto.getScene().getWindow();

       // Crea un FileChooser
       FileChooser fileChooser = new FileChooser();

       // Configura il titolo della finestra di dialogo
       fileChooser.setTitle("Seleziona un file");

       // Imposta un filtro per i tipi di file
       fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Immagini", "*.png", "*.jpg", "*.jpeg"),
               new FileChooser.ExtensionFilter("Tutti i file", "*.*")
       );

       // Apri la finestra di dialogo e ottieni il file selezionato
       File selectedFile = fileChooser.showOpenDialog(currentWindow);
       if (selectedFile != null) {
           System.out.println("File selezionato: " + selectedFile.getAbsolutePath());

           try{
               ImageRequester.uploadRequester(selectedFile.getAbsolutePath());
           }catch(InterruptedException e){
               throw e;
           }

       } else {
           System.out.println("Nessun file selezionato.");
       }





   }


   public void cercaComuni() {
       textComune.textProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue.length() >= 2){

               String comune= textComune.getText();

               RicercaComuniRequester.getSuggestions(comune,ListMaps);
               ListMaps.setVisible(true);
           }else{

               ListMaps.getItems().clear();
               ListMaps.setVisible(false);
           }
       });
       ListMaps.setOnMouseClicked(event -> {
           String selected = ListMaps.getSelectionModel().getSelectedItem();
           if(selected != null){
               textComune.setText(selected);
               ListMaps.getItems().clear();
               ListMaps.setVisible(false);
           }
       });




   }
}
