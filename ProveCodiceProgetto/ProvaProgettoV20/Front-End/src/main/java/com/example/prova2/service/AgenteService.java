package com.example.prova2.service;
import com.example.prova2.dto.AgenteDatiDTO;
import com.example.prova2.model.Agente;
import com.example.prova2.factory.HttpRequestBuilderFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AgenteService {

    private AgenteService(){}

    private static final String BASE_URL = "http://localhost:9094/agente/add";


    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static boolean addAgente(Agente a) throws IOException, InterruptedException {
        String jsonInputString = String.format("""
                {
                    "accountAgente": {"email": "%s","password": "%s"},
                    "gestoreRiferimento": {"cf": "%s"},
                    "nome": "%s",
                    "cognome": "%s",
                     "cf": "%s",
                     "telefono": "%s"
                }
            """,a.getAccountAgente().getEmail()
                ,a.getAccountAgente().getPassword(),a.getGestoreRiferimento().getCf()
                , a.getNome(),a.getCognome(),a.getCf(),a.getTelefono());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return addTodoResponse.body().equals("true");
    }

    public static AgenteDatiDTO getAgente(String email){
        String url = "http://localhost:9094/agente/dati?email="+email;
        HttpRequest request = HttpRequestBuilderFactory.buildGET(url);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), AgenteDatiDTO.class);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
