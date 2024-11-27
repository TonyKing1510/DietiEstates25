module com.example.prova2.View {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires javafx.graphics;


    opens com.example.prova2.View to javafx.fxml;
    exports com.example.prova2.View;
    exports com.example.prova2.Controller;
    opens com.example.prova2.Controller to javafx.fxml;
}