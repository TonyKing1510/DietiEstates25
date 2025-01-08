module com.example.prova2.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires jdk.jdi;
    requires com.google.gson;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens com.example.prova2.view to javafx.fxml;
    opens com.example.prova2.model to com.fasterxml.jackson.databind;
    exports com.example.prova2.view;
    exports com.example.prova2.controller;
    exports com.example.prova2.model to com.fasterxml.jackson.databind;
    opens com.example.prova2.controller to javafx.fxml;

}