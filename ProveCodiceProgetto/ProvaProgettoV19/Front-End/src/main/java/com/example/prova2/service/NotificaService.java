package com.example.prova2.service;
import com.example.prova2.dto.NotificaDTO;
import com.example.prova2.model.Notifica;
import com.example.prova2.factory.HttpRequestBuilderFactory;
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

public class NotificaService {

    private NotificaService(){}

    private static final HttpClient client = HttpClient.newHttpClient();
    private static ObjectMapper objectMapper = new ObjectMapper(); //per deserializzare la notifica


    public static List<Notifica> getNotificationsForAdmin(String cf) throws IOException, InterruptedException {
        String baseUrl="http://localhost:9094/not?cf="+cf;
        HttpRequest request = HttpRequestBuilderFactory.buildGET(baseUrl);
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

    public static void setNotificationAccepted(int idNotifica) {
        String url = "http://localhost:9094/not";
        setNotAcc(url + "/" + idNotifica + "/accetta");
    }

    public static void setNotificationRejected(int idNotifica) {
        String url = "http://localhost:9094/not";
        setNotRej(url + "/" + idNotifica + "/elimina");
    }

    public static NotificaDTO setNotAcc(String url) {
        HttpRequest request = HttpRequestBuilderFactory.buildPOST(url,HttpRequest.BodyPublishers.noBody().toString());
        return mandRichiestaNotifica(request);
    }

    public static NotificaDTO setNotRej(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .header("Content-Type", "application/json")
                .build();
        return mandRichiestaNotifica(request);
    }

    private static NotificaDTO mandRichiestaNotifica(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(),NotificaDTO.class);
        }catch (IOException e) {
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
