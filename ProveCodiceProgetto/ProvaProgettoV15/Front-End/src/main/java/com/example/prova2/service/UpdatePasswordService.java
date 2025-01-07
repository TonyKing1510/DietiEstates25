package com.example.prova2.service;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.utility.HttpRequestBuilderFactory;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UpdatePasswordService {
    private UpdatePasswordService(){}

    private static final String BASE_URL = "http://localhost:9094/admin/update";


    private static final HttpClient client = HttpClient.newHttpClient();

    public static boolean updatePassword(String nuovaPass,GestoreAgenziaImmobiliare gestore) {
        String jsonInputString = String.format("""
                {
                  "accountGestore": {
                        "password": "%s"
                                           },
                  "cf": "%s"
                }
            """, nuovaPass,gestore.getCf());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        try {
            HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
            return addTodoResponse.body().equals("true");
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Errore","Errore nella richiesta");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlert("Errore","Errore nella richiesta");
            return false;
        }
    }
}
