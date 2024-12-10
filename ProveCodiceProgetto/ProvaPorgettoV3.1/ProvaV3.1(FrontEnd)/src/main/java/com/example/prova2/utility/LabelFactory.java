package com.example.prova2.utility;

import javafx.scene.control.Label;

public class LabelFactory {

    private LabelFactory() {}


    public static Label createLabel(String labelName) {
        Label label = new Label(labelName);
        label.setLayoutX(165.0);
        label.setLayoutY(31.0);
        return label;
    }
}
