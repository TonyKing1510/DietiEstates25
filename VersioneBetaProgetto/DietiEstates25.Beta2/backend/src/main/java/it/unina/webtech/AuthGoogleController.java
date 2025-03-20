package it.unina.webtech;

import it.unina.webtech.dto.response.AgenteDTO;
import it.unina.webtech.service.ClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static it.unina.webtech.AuthController.createJWT;

@Path("/auth")
public class AuthGoogleController {

    private static final String CLIENT_ID = "603934349943-q9t7jpn8i2hb0ivlgg0u1rco1nm6hji5.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:9094/auth/exchange_token";
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    @GET
    @Path("/exchange_token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response exchangeToken(@QueryParam("code") String code) {
        try {
            String tokenResponse = fetchAccessToken(code);
            JsonNode tokenJson = new ObjectMapper().readTree(tokenResponse);
            String accessToken = tokenJson.get("access_token").asText();
            String userInfoResponse = fetchUserInfo(accessToken);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode userInfoJson = objectMapper.readTree(userInfoResponse);
            String googleId = userInfoJson.get("id").asText();
            String email = userInfoJson.get("email").asText();
            String token = createJWT(email, TimeUnit.DAYS.toMillis(365));
            AgenteDTO response=ClienteService.addCliente(email, googleId);
            if(response != null) {
                response.setToken(token);
            }
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'autenticazione").build();
        }
    }

    private String fetchAccessToken(String code) throws IOException {
        String params = "code=" + code +
                "&client_id=" + CLIENT_ID +
                "&client_secret=" + CLIENT_SECRET +
                "&redirect_uri=" + REDIRECT_URI +
                "&grant_type=authorization_code";

        return sendPostRequest(TOKEN_URL, params);
    }

    private String fetchUserInfo(String accessToken) throws IOException {
        URL url = new URL(USER_INFO_URL + "?access_token=" + accessToken);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        return new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    private String sendPostRequest(String url, String params) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(params.getBytes(StandardCharsets.UTF_8));
        }
        return new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
