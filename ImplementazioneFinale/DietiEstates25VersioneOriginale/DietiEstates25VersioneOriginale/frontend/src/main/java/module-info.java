module com.example.prova2.view {
    requires javafx.web;
    requires javafx.fxml;
    requires jdk.jdi;
    requires com.google.gson;
    requires java.net.http;
    requires javafx.media;
    requires org.json;
    requires com.calendarfx.view;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires jdk.jsobject;
    requires software.amazon.awssdk.services.s3;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.auth;
    requires ical4j.core;
    requires javafx.graphics;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.client.json.jackson2;
    requires commons.logging;

    opens com.example.prova2.dto to com.fasterxml.jackson.databind;
    opens com.example.prova2.view to javafx.fxml;
    opens com.example.prova2.model to com.fasterxml.jackson.databind;
    exports com.example.prova2.view;
    exports com.example.prova2.controller;
    exports com.example.prova2.model to com.fasterxml.jackson.databind;
    opens com.example.prova2.controller to javafx.fxml;
    exports com.example.prova2.controller.dashBoard;
    opens com.example.prova2.controller.dashBoard to javafx.fxml;
    exports com.example.prova2.controller.login;
    opens com.example.prova2.controller.login to javafx.fxml;
    exports com.example.prova2.controller.notifiche;
    opens com.example.prova2.controller.notifiche to javafx.fxml;
    exports com.example.prova2.controller.modificaProfilo;
    opens com.example.prova2.controller.modificaProfilo to javafx.fxml;
    exports com.example.prova2.controller.registrazione;
    opens com.example.prova2.controller.registrazione to javafx.fxml;
    exports com.example.prova2.controller.prenotaVisita;
    opens com.example.prova2.controller.prenotaVisita to javafx.fxml;

}