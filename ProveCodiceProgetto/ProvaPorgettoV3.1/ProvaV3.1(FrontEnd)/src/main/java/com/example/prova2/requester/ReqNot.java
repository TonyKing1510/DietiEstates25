package com.example.prova2.requester;

import com.example.prova2.Model.Notification;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ReqNot {

    private static final String BASE_URL = "http://localhost:9094/not";
    private static final HttpClient client = HttpClient.newHttpClient();

    // Metodo per deserializzare la risposta JSON usando Gson
    public static List<Notification> parseJson(String json){
        // Crea un'istanza di Gson
        Gson gson = new Gson();

        // Deserializza il JSON in una lista di notifiche
        return gson.fromJson(json, new TypeToken<List<Notification>>() {}.getType());
    }
    // Metodo per ottenere tutte le notifiche
    public static List<Notification> getNotifications() throws IOException, InterruptedException {
        // Crea la richiesta GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .GET() // Metodo GET
                .build();

        // Invia la richiesta e ottieni la risposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Se la risposta è OK (200), procediamo con la deserializzazione
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            // Usa Gson per controllare se la risposta è un array JSON
            Gson gson = new Gson();
            // Imposta il lettore JSON in modalità "lenient" per gestire il JSON malformato
            JsonReader reader = new JsonReader(new StringReader(responseBody));
            reader.setLenient(true);

            // Utilizza JsonParser per eseguire il parsing della risposta JSON
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Se la risposta è un array, deserializza in una lista di notifiche
            if (jsonElement.isJsonArray()) {
                return gson.fromJson(responseBody, new TypeToken<List<Notification>>(){}.getType());
            } else {
                // Se non è un array, gestisci il caso di errore o stringa
                System.out.println("Errore o risposta non in formato array JSON: " + responseBody);
                return null;
            }
        }
        return null; // Se la risposta non è OK, restituiamo null
    } // Se la risposta non è OK, restituiamo null
}
