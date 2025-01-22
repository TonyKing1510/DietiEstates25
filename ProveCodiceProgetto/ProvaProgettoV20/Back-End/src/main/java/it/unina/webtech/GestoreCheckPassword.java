package it.unina.webtech;


import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.PasswordCheckGestoreService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("gestore/checkPassword")
public class GestoreCheckPassword {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public static boolean checkPassword(GestoreAgenziaImmobiliare g) {
        if(g.getCf().isEmpty()){
            return false;
        }
        return PasswordCheckGestoreService.checkPassword(g);
    }
}
