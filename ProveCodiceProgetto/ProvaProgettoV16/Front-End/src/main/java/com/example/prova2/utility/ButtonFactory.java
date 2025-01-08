package com.example.prova2.utility;

import javafx.scene.control.Button;

public class ButtonFactory {

    private ButtonFactory(){}

    public static final String STILEBOTTONE= "-fx-background-color : rgba(255, 255, 255, 0.2); " +
            "-fx-backgrounds-radius : 10; " +
            "-fx-border-width: 2; " +
            "-fx-text-fill: #f3f6f4; ";


    public static Button createButton() {
        Button button = new Button();
        button.setPrefWidth(175);
        button.setPrefHeight(93.6);

        button.setStyle(STILEBOTTONE);
        return button;
    }
}
