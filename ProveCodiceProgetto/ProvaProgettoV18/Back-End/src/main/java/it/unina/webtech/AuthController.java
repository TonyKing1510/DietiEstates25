package it.unina.webtech;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.LoginUtenteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Path("auth")
public class AuthController {

    private static final String ISSUER = "todo-rest-api";
    private static final Algorithm algorithm = Algorithm.HMAC256("very_secret_key_not_to_share");
    private static final JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();

    public static String getUsernameClaim(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("username").asString();
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AccountSemplice credenziali,@QueryParam("usr") String type){
        if (isCredenzialiNull(credenziali)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        if(isUsrAGestore(type)) {
            return gestisciCasoUsrGestore(credenziali);
        }else {
            return gestisciCasoUsrAgenteOCliente(credenziali, type);
        }
    }

    private static boolean isCredenzialiNull(AccountSemplice credenziali) {
        return credenziali.getUsername() == null || credenziali.getPassword() == null;
    }

    private static boolean isUsrAGestore(String type) {
        return type.equals("gestore");
    }

    private Response gestisciCasoUsrAgenteOCliente(AccountSemplice credenziali, String type) {
        if (LoginUtenteService.exists(credenziali, type)) {
            String token = createJWT(credenziali.getUsername(), TimeUnit.DAYS.toMillis(365));
            return Response
                    .status(Response.Status.OK)
                    .entity(token)
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
    }

    private Response gestisciCasoUsrGestore(AccountSemplice credenziali) {
        GestoreAgenziaImmobiliareDTO dto = LoginUtenteService.existsGestore(credenziali);
        if(isCredenzialiSbagliate(dto)){
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new GestoreAgenziaImmobiliareDTO(null,false,true,null,null,false))
                    .build();
        }else{
            String token = createJWT(credenziali.getUsername(), TimeUnit.DAYS.toMillis(365));
            return Response
                    .status(Response.Status.OK)
                    .entity(new GestoreAgenziaImmobiliareDTO(token,dto.getAdditionalInfo(),false,dto.getCf(),dto.getAgenzia(),dto.isFattoPrimoAccesso()))
                    .build();
        }
    }

    private static boolean isCredenzialiSbagliate(GestoreAgenziaImmobiliareDTO gestore) {
        return gestore==null;
    }


    private String createJWT(String username, long ttlMillis) {

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + ttlMillis))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);

        return token;
    }

    public static boolean validateToken(String token){
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
