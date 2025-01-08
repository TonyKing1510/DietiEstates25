package com.example.prova2.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


public class GetGestoreService {
    private static final String url = "http://localhost:9094/gestore";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static Map<String,Object> getGestori(String username) throws IOException, InterruptedException{
        return getGestore(url+"/dati?username="+username);
    }

    public static Map<String,Object> getGestore(String url) throws IOException, InterruptedException{
        // Creazione della richiesta GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()  // Metodo GET per la richiesta
                .build();

        try {
            // Invio della richiesta HTTP e ricezione della risposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica del codice di stato della risposta (200 OK)
            if (response.statusCode() == 200) {
                // Deserializzazione della risposta JSON come una mappa
                return objectMapper.readValue(response.body(), Map.class);
            } else {
                // Se il codice di stato non è 200, ritorna null o un'eccezione
                throw new IOException("Errore nel recupero dei dati, codice di stato: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw e;  // Rilancia l'eccezione per il trattamento a un livello superiore
        }
    }
}
