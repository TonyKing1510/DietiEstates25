package com.example.prova2.requester;
import com.example.prova2.model.AccountSemplice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class Requester {


    private Requester(){}

    private static final String BASE_URL = "http://localhost:9094/user";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void request(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        // Corpo della richiesta JSON con valori dinamici per email e password
        String jsonBody = String.format("{\"mail\":\"%s\", \"password\":\"%s\"}", accountSemplice.getMail(), accountSemplice.getPassword());

        HttpRequest addTodoRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(addTodoResponse.toString());

        System.out.println("\nAdd To-do Response");
        System.out.println("Status code: " + addTodoResponse.statusCode());
        System.out.println("Body       : " + addTodoResponse.body());

    }
}
