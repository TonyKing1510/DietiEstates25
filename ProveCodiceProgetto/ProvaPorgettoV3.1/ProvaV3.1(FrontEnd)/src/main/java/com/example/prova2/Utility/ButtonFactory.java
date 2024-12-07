package com.example.prova2.Utility;

import javafx.scene.control.Button;

public class ButtonFactory {
    public static Button createButton() {
        Button button = new Button();
        // Imposta dimensioni
        button.setPrefWidth(374.4);
        button.setPrefHeight(93.6);

// Imposta stile del bottone
        button.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: #011638; " +  // Colore del bordo
                        "-fx-border-width: 2; " +        // Spessore del bordo
                        "-fx-border-radius: 5; "         // Raggio del bordo arrotondato
        );
        return button;
    }
}
