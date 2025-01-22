package it.unina.webtech;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.unina.webtech.dto.AgenteDTO;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
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
        return credenziali.getEmail() == null || credenziali.getPassword() == null;
    }

    private static boolean isUsrAGestore(String type) {
        return type.equals("gestore");
    }

    private Response gestisciCasoUsrAgenteOCliente(AccountSemplice credenziali, String type) {
        AgenteDTO dto=LoginUtenteService.exists(credenziali, type);
        if(nonSièVerificatoErroreInterno(dto)){
            if(isCredenzialiEsatte(dto)) {
                return tokenPerAccesso(credenziali,dto);
            }else{
                return utenteNonAuth(dto);
            }
        } else {
            return serverInternalError(dto);
        }
    }

    private static Response serverInternalError(AgenteDTO dto) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(dto)
                .build();
    }

    private static Response utenteNonAuth(AgenteDTO dto) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(dto)
                .build();
    }

    private static Response tokenPerAccesso(AccountSemplice credenziali,AgenteDTO dto) {
        String token = createJWT(credenziali.getUsername(), TimeUnit.DAYS.toMillis(365));
        dto.setToken(token);
        return Response
                .status(Response.Status.OK)
                .entity(dto)
                .build();
    }

    private static boolean isCredenzialiEsatte(AgenteDTO dto) {
        return !dto.isCredenzialiSbagliate();
    }

    private static boolean nonSièVerificatoErroreInterno(AgenteDTO dto) {
        return !dto.isErroreInterno();
    }

    private Response gestisciCasoUsrGestore(AccountSemplice credenziali) {
        GestoreAgenziaImmobiliareDTO dto = LoginUtenteService.existsGestore(credenziali);
        if(isCredenzialiSbagliate(dto)){
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new GestoreAgenziaImmobiliareDTO(null,false,true,null,null))
                    .build();
        }else{
            String token = createJWT(credenziali.getUsername(), TimeUnit.DAYS.toMillis(365));
            return Response
                    .status(Response.Status.OK)
                    .entity(new GestoreAgenziaImmobiliareDTO(token,dto.getAdditionalInfo(),false,dto.getCf(),dto.getAgenzia()))
                    .build();
        }
    }

    private static boolean isCredenzialiSbagliate(GestoreAgenziaImmobiliareDTO gestore) {
        return gestore==null;
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
            System.out.println(e.getMessage());
            return false;
        }
    }

}
