package com.example.prova2.service;
import com.example.prova2.model.Notifica;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private NotificationService(){}

    private static final HttpClient client = HttpClient.newHttpClient();
    private static ObjectMapper objectMapper = new ObjectMapper(); //per deserializzare la notifica


    public static List<Notifica> getNotificationsForAdmin(String cf) throws IOException, InterruptedException {
        String baseUrl="http://localhost:9094/not?cf="+cf;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 200) {
            try {
                List<Notifica> notifiche = objectMapper.readValue(response.body(), new TypeReference<List<Notifica>>() {
                });
                if (!notifiche.isEmpty()) {
                    return notifiche;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}
