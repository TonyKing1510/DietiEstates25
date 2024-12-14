package com.example.prova2.requester;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImageRequester {

    private ImageRequester(){}

    private static final String BASE_URL = "http://localhost:9094/upload";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void uploadRequester(String path) throws IOException, InterruptedException {
        String jsonBody = String.format("{\"path\":\"%s\"}", path);
        HttpRequest addTodoRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(addTodoResponse.body());


    }
}
