package com.example.prova2.requester;
import com.example.prova2.model.Admin;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.utility.HttpRequestBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class LoginAdminRequester {


    private static final String BASE_URL = "http://localhost:9094/login/admin";


    private static final HttpClient client = HttpClient.newHttpClient();

    public boolean effettuaLoginAdmin(GestoreAgenziaImmobiliare g){
        String jsonInputString = String.format("""
                {
                      "agenziaAppartenente": {
                        "nomeAgenzia": "%s"
                      },
                      "accountGestore": {
                        "username": "%s",
                        "password": "%s"
                      },
                      "isAdmin": "%b",
                      "cf": "%s"
                    }
            """, g.getAgenziaAppartenente().getNomeAgenzia(), g.getAccountGestore().getUsername(),
                g.getAccountGestore().getPassword(),
                g.isAdmin(), g.getCf());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        try {
            HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(addTodoResponse.body());
            System.out.println(addTodoResponse);
            return addTodoResponse.body().equals("true");
        } catch (IOException e) {
            AlertFactory.generateFailAlert("Errore","Si prega di controllare la connessione!");
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlert("Errore","Si prega di controllare la connessione!");
            return false;
        }
    }
}
