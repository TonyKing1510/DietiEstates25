package com.example.prova2.utility;

import javafx.scene.control.TextArea;

public class TextAreaFactory {

    // Costruttore privato per evitare la creazione di istanze della factory
    private TextAreaFactory() {}

    // Metodo per creare un TextArea con contenuto
    public static TextArea createTextArea(String content) {
        TextArea textArea = new TextArea();
        textArea.setText(content);
        textArea.setEditable(false);  // Imposta il TextArea come non modificabile
        textArea.setWrapText(true);    // Abilita il ritorno a capo automatico
        textArea.setPrefHeight(402);   // Imposta l'altezza preferita
        textArea.setPrefWidth(400);    // Imposta la larghezza preferita
        return textArea;
    }
}
