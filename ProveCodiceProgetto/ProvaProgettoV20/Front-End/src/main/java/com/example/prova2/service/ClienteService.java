package com.example.prova2.service;
import com.example.prova2.dto.ClienteDTO;
import com.example.prova2.factory.HttpRequestBuilderFactory;
import com.example.prova2.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteService {

    private static final HttpClient client = HttpClient.newHttpClient();

    private ClienteService() {
    }


    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static ClienteDTO registrati(Cliente cliente) {
        String query = buildString(cliente);
        String url = "http://localhost:9094/cliente/add";
        HttpRequest request = HttpRequestBuilderFactory.buildPOST(url, query);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return objectMapper.readValue(response.body(), ClienteDTO.class);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static String buildString(Cliente c) {
        return String.format("""
                            {
                                "accountCliente": {"email": "%s", "password": "%s"},
                                "nome": "%s",
                                "cognome": "%s",
                                "telefono": "%s"
                            }
                        """, c.getAccountCliente().getMail(), c.getAccountCliente().getPassword(), c.getNome(), c.getCognome(),
                c.getTelefono());
    }
}