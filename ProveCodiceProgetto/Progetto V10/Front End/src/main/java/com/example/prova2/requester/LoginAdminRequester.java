package com.example.prova2.requester;
import com.example.prova2.model.Admin;
import com.example.prova2.utility.HttpRequestBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class LoginAdminRequester {


    private static final String BASE_URL = "http://localhost:9094/login/admin";


    private static final HttpClient client = HttpClient.newHttpClient();

    public String effettuaLoginAdmin(Admin admin) throws IOException, InterruptedException {
        String jsonInputString = String.format("""
                {
                  "cf": "%s",
                  "agenziaAppartenente": {"nomeAgenzia": "%s"},
                  "nome": "%s",
                  "cognome": "%s",
                  "accountAmministratore" : {"email" :  "%s"},
                  "isAdmin" : %b
                }
            """, admin.getCf(), admin.agenziaAppartenente.getNomeAgenzia(), admin.getNome(), admin.getCognome(), admin.getAccountAmministratore().getEmail(), admin.isAdmin());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(BASE_URL,jsonInputString);
        HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return addTodoResponse.body();
    }
}
