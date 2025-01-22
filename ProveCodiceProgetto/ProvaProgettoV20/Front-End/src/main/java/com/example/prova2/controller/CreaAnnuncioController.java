package com.example.prova2.controller;

import com.example.prova2.service.ImageService;
import com.example.prova2.service.RicercaComuniService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;

public class CreaAnnuncioController {
    public HBox fotoInserite;

    public ImageView contenitoreImage;
    @FXML
    private Button btnFoto;

    @FXML
    private TextField textComune;

    @FXML
    private ListView<String> listMaps;

    public void uploadImage() throws IOException, InterruptedException {
        Window currentWindow = btnFoto.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Immagini", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("Tutti i file", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(currentWindow);
        if (selectedFile != null) {
            contenitoreImage.setImage(new Image(selectedFile.toURI().toString()));
            try{
                ImageService.uploadRequester(selectedFile.getAbsolutePath());
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
                RicercaComuniService.getSuggestions(comune, listMaps);
                listMaps.setVisible(true);
            }else{
                listMaps.getItems().clear();
                listMaps.setVisible(false);
            }
        });
        listMaps.setOnMouseClicked(event -> {
            String selected = listMaps.getSelectionModel().getSelectedItem();
            if(selected != null){
                textComune.setText(selected);
                listMaps.getItems().clear();
                listMaps.setVisible(false);
            }
        });
    }
}