module com.example.prova2.View {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires jdk.jdi;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.media;


    opens com.example.prova2.View to javafx.fxml;
    exports com.example.prova2.View;
    exports com.example.prova2.Controller;
    opens com.example.prova2.Controller to javafx.fxml;

    opens com.example.prova2.Model to com.google.gson; // Apre il pacchetto al modulo Gson
}