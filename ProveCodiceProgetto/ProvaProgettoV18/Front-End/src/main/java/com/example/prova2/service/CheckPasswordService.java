package com.example.prova2.service;

import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.factory.HttpRequestBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CheckPasswordService {

    private static final String BASE_URL = "http://localhost:9094/gestore/checkPassword";


    private static final HttpClient client = HttpClient.newHttpClient();

    public static boolean checkPassword(GestoreAgenziaImmobiliare g,String password) {
        String jsonInputString = String.format("""
                {
                  "accountGestore": {
                          "password": "%s"
                        },
                  "cf": "%s"
                }
            """, password,g.getCf());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        try {
            HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
            return addTodoResponse.body().equals("true");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
