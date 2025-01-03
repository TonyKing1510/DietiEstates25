package com.example.prova2.utility;

import javafx.scene.control.Button;

public class ButtonFactory {

    private ButtonFactory(){}

    public static final String STILEBOTTONE= "-fx-background-color: transparent; " +
            "-fx-border-color: #011638; " +
            "-fx-border-width: 2; " +
            "-fx-border-radius: 5; ";


    public static Button createButton() {
        Button button = new Button();
        button.setPrefWidth(374.4);
        button.setPrefHeight(93.6);

        button.setStyle(STILEBOTTONE);
        return button;
    }
}
