package com.example.prova2.service;
import com.example.prova2.model.Agente;
import com.example.prova2.utility.AlertFactory;
import com.example.prova2.utility.HttpRequestBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddAgenteService {

    private AddAgenteService(){}

    private static final String BASE_URL = "http://localhost:9094/agente/add";


    private static final HttpClient client = HttpClient.newHttpClient();


    public static boolean addAgente(Agente a) throws IOException, InterruptedException {
        String jsonInputString = String.format("""
                {
                    "accountAgente": {
                                  "username": "%s",
                                  "password": "%s",
                                  "email": "%s"
                                },
                    "gestoreRiferimento": {
                                  "cf": "%s"
                                    },
                    "nome": "%s",
                    "cognome": "%s",
                     "cf": "%s",
                     "telefono": "%s",
                     "via": "%s",
                      "numeroCivico": "%s"
                }
            """,a.getAccountAgente().getUsername() ,a.getAccountAgente().getPassword()
                ,a.getAccountAgente().getEmail(),a.getGestoreRiferimento().getCf()
                , a.getNome(),a.getCognome(),a.getCf(),a.getTelefono(),a.getVia(),a.getNumeroCivico());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        try {
            HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
            return addTodoResponse.body().equals("true");
        }
        catch (InterruptedException e) {
            AlertFactory.generateFailAlert("Errore","Errore nella richiesta");
            return false;
        }
    }
}
