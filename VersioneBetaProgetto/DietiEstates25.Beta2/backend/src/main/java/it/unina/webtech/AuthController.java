package it.unina.webtech;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import it.unina.webtech.dto.request.LoginRequestDTO;
import it.unina.webtech.dto.response.LoginUtenteResponse;
import it.unina.webtech.service.AuthService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Path("auth")
public class AuthController {

    private static final String ISSUER = "todo-rest-api";
    private static final Algorithm algorithm = Algorithm.HMAC256(System.getenv("SECRET_KEY"));
    private static final JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO credenziali){
        LoginUtenteResponse response=AuthService.login(credenziali.getEmail(),credenziali.getPassword());
        if(response != null){
            response.setToken(createJWT(credenziali.getEmail(), TimeUnit.DAYS.toMillis(365)));
            return Response.ok(response).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static String createJWT(String username, long ttlMillis) {

        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + ttlMillis))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public static boolean validateToken(String token){
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

}
