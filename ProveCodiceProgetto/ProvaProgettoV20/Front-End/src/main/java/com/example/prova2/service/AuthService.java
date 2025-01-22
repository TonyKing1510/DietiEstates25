package com.example.prova2.service;
import com.example.prova2.dto.AgenteDTO;
import com.example.prova2.dto.GestoreAgenziaImmobiliareDTO;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.factory.HttpRequestBuilderFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class AuthService {

    private static final HttpClient client = HttpClient.newHttpClient();

    private AuthService(){}

    private static final String queryJson = """
                {
                     "email" : "%s",
                     "password" : "%s"
                }
            """;


    private static final String query = "http://localhost:9094/auth?usr=";

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static GestoreAgenziaImmobiliareDTO requestLoginAdmin(AccountSemplice accountSemplice) throws InterruptedException, IOException {
        String url = query + "gestore";
        String jsonInputString = String.format(queryJson, accountSemplice.getMail(), accountSemplice.getPassword());
        HttpRequest addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        System.out.println(queryJson);
        HttpResponse<String> response = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), GestoreAgenziaImmobiliareDTO.class);
    }

    public static AgenteDTO requestLoginAgente(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        String url = query + "agente";
        return getAgenteDTO(accountSemplice, url);
    }

    public static AgenteDTO requestLoginCliente(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        String url = query + "cliente";
        return getAgenteDTO(accountSemplice, url);
    }

    private static AgenteDTO getAgenteDTO(AccountSemplice accountSemplice, String url) throws IOException, InterruptedException {
        String jsonInputString = String.format(queryJson, accountSemplice.getMail(), accountSemplice.getPassword());
        HttpRequest addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        HttpResponse<String> response = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(),AgenteDTO.class);
    }




}
