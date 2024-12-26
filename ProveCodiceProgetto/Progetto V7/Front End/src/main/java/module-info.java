module com.example.prova2.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires jdk.jdi;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.media;


    opens com.example.prova2.view to javafx.fxml;
    exports com.example.prova2.view;
    exports com.example.prova2.controller;
    opens com.example.prova2.controller to javafx.fxml;

    opens com.example.prova2.model to com.google.gson; // Apre il pacchetto al modulo Gson
}