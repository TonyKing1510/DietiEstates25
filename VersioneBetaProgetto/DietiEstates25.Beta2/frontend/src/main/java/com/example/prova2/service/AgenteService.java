package com.example.prova2.service;
import com.example.prova2.dto.DatiDTO;
import com.example.prova2.dto.GetAllRicercheResponse;
import com.example.prova2.dto.UpdateDatiResponseDTO;
import com.example.prova2.model.Agente;
import com.example.prova2.factory.HttpRequestBuilderFactory;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.model.Utente;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.example.prova2.service.ClienteService.getDati;
import static com.example.prova2.service.GestoreService.getGetAllRicercheResponses;
import static com.example.prova2.service.NotificaService.getResponse;

public class AgenteService {

    private AgenteService(){}

    private static final String BASE_URL = "http://localhost:9094/agente/add";


    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static boolean addAgente(Agente a, GestoreAgenziaImmobiliare g) throws IOException, InterruptedException {
        String jsonInputString = String.format("""
                {
                    "email": "%s",
                    "password": "%s",
                    "cfGestore": "%s",
                    "nome": "%s",
                    "cognome": "%s",
                     "cf": "%s",
                     "telefono": "%s"
                }
            """,a.getAccountAgente().getEmail()
                ,a.getAccountAgente().getPassword(),a.getGestoreRiferimento().getCf()
                , a.getNome(),a.getCognome(),a.getCf(),a.getTelefono());
        HttpRequest addTodoRequest;
        addTodoRequest = HttpRequestBuilderFactory.buildPOSTtoken(BASE_URL,jsonInputString,g.getToken());
        HttpResponse<String> addTodoResponse = mandaRichiesta(addTodoRequest);
        return rispostaAndataBuonFine(addTodoResponse);
    }

    public static DatiDTO getAgente(String email,String token){
        String url = "http://localhost:9094/agente/dati?email="+email;
        HttpRequest request = HttpRequestBuilderFactory.buildGetWithToken(url,token);
        try {
            HttpResponse<String> response = mandaRichiesta(request);
            return objectMapper.readValue(response.body(), DatiDTO.class);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static List<String> getFotoAgente(String cf){
        String url = "http://localhost:9094/image/fetchByCf?codicefiscale="+cf;
        HttpRequest request = HttpRequestBuilderFactory.buildGET(url);
        try{
            HttpResponse<String> response = mandaRichiesta(request);
            return objectMapper.readValue(response.body(), new TypeReference<List<String>>() {});
        } catch (RuntimeException | IOException e) {
            return Collections.emptyList();
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }
    }


    public static CompletableFuture<UpdateDatiResponseDTO> updateDatiAgente(Agente agente){
        String url = "http://localhost:9094/agente/updatedati?cf="+agente.getCf();
        String jsonInputString = String.format("""
                {
                    "nome" : "%s",
                    "cognome" : "%s",
                    "telefono" : "%s",
                    "email" : "%s"
                }
            """,agente.getNome(),agente.getCognome(),agente.getTelefono(),agente.getAccountAgente().getEmail());
        return getDati(url, jsonInputString, client, objectMapper,agente.getToken());
    }

    public static boolean updateBio(String cf,String newBio,String token){
        String url = "http://localhost:9094/agente/updateBio?cf="+cf;
        String jsonInputString = String.format("""
                {
                   "biografia" : "%s"
                }
            """,newBio);
        return getResponse(url, jsonInputString,token);
    }

    public static List<GetAllRicercheResponse> getAllRicerche(Utente utente){
        String url = "http://localhost:9094/agente/ricerche/"+utente.getCf();
        return getGetAllRicercheResponses(utente, url, client, objectMapper);
    }

    public static boolean rispostaAndataBuonFine(HttpResponse<String> response) {
        return response.statusCode() == 200;
    }


    public static HttpResponse<String> mandaRichiesta(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


}