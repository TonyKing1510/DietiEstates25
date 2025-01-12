package com.example.prova2.service;
import com.example.prova2.dto.GestoreAgenziaImmobiliareDTO;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.factory.HttpRequestBuilderFactory;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class AuthService {

    private static final HttpClient client = HttpClient.newHttpClient();

    private AuthService(){}

    private static String queryJson = """
                {
                    "username" : "%s",
                     "password" : "%s"     
                }
            """;


    private static String query = "http://localhost:9094/auth?usr=";

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static GestoreAgenziaImmobiliareDTO requestLoginAdmin(AccountSemplice accountSemplice) throws InterruptedException, IOException {
        String url = query + "gestore";
        String jsonInputString = String.format(queryJson, accountSemplice.getUsername(), accountSemplice.getPassword());
        HttpRequest addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        try {
            HttpResponse<String> response = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return objectMapper.readValue(response.body(), GestoreAgenziaImmobiliareDTO.class);
        }
        catch (InterruptedException e) {
           Thread.currentThread().interrupt();
           throw e;
        }
        catch (IOException e) {
            throw e;
        }
    }

    public static boolean requestLoginAgente(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        String url = query + "agente";
        String jsonInputString = String.format(queryJson, accountSemplice.getUsername(), accountSemplice.getPassword());
        HttpRequest addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        HttpResponse<String> response = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 200;
    }

    public static boolean requestLoginCliente(AccountSemplice accountSemplice) throws IOException, InterruptedException {
        String url = query + "cliente";
        String jsonInputString = String.format(queryJson, accountSemplice.getUsername(), accountSemplice.getPassword());
        HttpRequest addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        HttpResponse<String> response = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 200;
    }

    public static GestoreAgenziaImmobiliareDTO effettuaPrimoLoginGestore(GestoreAgenziaImmobiliare g){
        String url = "http://localhost:9094/login/admin";
        String jsonInputString = makeString(g);
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        try {
            HttpResponse<String> response = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), GestoreAgenziaImmobiliareDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        }
    }

    private static String makeString(GestoreAgenziaImmobiliare g) {
        return String.format("""
                            {
                                  "agenziaAppartenente": {
                                    "nomeAgenzia": "%s"
                                  },
                                  "accountGestore": {
                                    "username": "%s",
                                    "password": "%s"
                                  },
                                  "isAdmin": "%b",
                                  "cf": "%s"
                                }
                        """, g.getAgenziaAppartenente().getNomeAgenzia(), g.getAccountGestore().getUsername(),
                g.getAccountGestore().getPassword(),
                g.isAdmin(), g.getCf());
    }


}
