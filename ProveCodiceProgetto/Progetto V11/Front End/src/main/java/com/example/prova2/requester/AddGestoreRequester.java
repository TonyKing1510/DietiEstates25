package com.example.prova2.requester;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.utility.HttpRequestBuilderFactory;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddGestoreRequester {

    private static final String BASE_URL = "http://localhost:9094/gestore/add";


    private static final HttpClient client = HttpClient.newHttpClient();

    public static boolean addGestore(GestoreAgenziaImmobiliare g) throws IOException, InterruptedException {
        String jsonInputString = String.format("""
                {
                  "adminAppartenente": {"cf": "%s","agenziaAppartenente": {
                          "nomeAgenzia": "%s"
                        }},
                        "accountGestore": {"username": "%s","password": "%s","email": "%s"},
                        "nome": "%s",
                        "cognome": "%s",
                        "cf": "%s",
                        "telefono": "%s",
                        "via": "%s",
                        "numeroCivico": "%s"
                }
            """, g.getAdminAppartenente().getCf(),g.getAdminAppartenente().agenziaAppartenente.getNomeAgenzia()
                 ,g.getAccountGestore().getUsername()
                , g.getAccountGestore().getPassword(),g.getAccountGestore().getEmail(),g.getNome(),
                  g.getCognome(),g.getCf(),g.getTelefono(),g.getVia(),g.getNumeroCivico());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return addTodoResponse.body().equals("true");
    }
}
