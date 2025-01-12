package com.example.prova2.service;
import com.example.prova2.dto.GestoreAgenziaImmobiliareDatiDTO;
import com.example.prova2.factory.HttpRequestBuilderFactory;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class GestoreService {
    private static String url = "http://localhost:9094/gestore";

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<String> getAllCfGestore() throws IOException, InterruptedException {
        return getAllGestore(url+"/cf");
    }

    public static List<String> getAllEmailGestore() throws IOException, InterruptedException {
        return getAllGestore(url+"/email");
    }

    public static List<String> getAllUsernameGestore() throws IOException, InterruptedException {
        return getAllGestore(url+"/username");
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

    public static GestoreAgenziaImmobiliareDatiDTO getGestoreByUsername(String username){
        String url = "http://localhost:9094/gestore/dati?username="+username;
        HttpRequest request = HttpRequestBuilderFactory.buildGET(url);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), GestoreAgenziaImmobiliareDatiDTO.class);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean addGestore(GestoreAgenziaImmobiliare g) throws IOException, InterruptedException {
        String url = "http://localhost:9094/gestore/add";
        return makeString(g, url, client);
    }

    static boolean makeString(GestoreAgenziaImmobiliare g, String url, HttpClient client) throws IOException, InterruptedException {
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
        addTodoRequest = HttpRequestBuilderFactory.buildPOST(url,jsonInputString);
        HttpResponse<String> addTodoResponse = client.send(addTodoRequest, HttpResponse.BodyHandlers.ofString());
        return addTodoResponse.body().equals("true");
    }
}
