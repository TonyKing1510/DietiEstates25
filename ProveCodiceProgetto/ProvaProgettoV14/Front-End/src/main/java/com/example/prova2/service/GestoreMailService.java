package com.example.prova2.service;
import com.example.prova2.utility.HttpRequestBuilderFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GestoreMailService {

    private GestoreMailService() {}

    private static final String URL = "http://localhost:9094/gestore";


    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper(); //per deserializzare la risposta in oggetti del model


    public static List<String> getAllEmailGestore() throws IOException, InterruptedException {
        return getAllGestore(URL+"/email");
    }

    public static List<String> getAllGestore(String what) throws IOException, InterruptedException {
        HttpRequest request = HttpRequestBuilderFactory.buildGET(what);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 200) {
            try {
                List<String> cfs = objectMapper.readValue(response.body(), new TypeReference<List<String>>() {
                });
                if (!cfs.isEmpty()) {
                    return cfs;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

}
