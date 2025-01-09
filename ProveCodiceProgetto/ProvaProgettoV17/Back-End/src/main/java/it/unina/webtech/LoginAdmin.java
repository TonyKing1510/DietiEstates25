package it.unina.webtech;

import it.unina.webtech.model.Admin;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.LoginAdminService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;

@Path("login/admin")
public class LoginAdmin {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean loginAdmin(GestoreAgenziaImmobiliare g){
        try {
            System.out.println(g.getAccountGestore().getUsername());
            System.out.println(g.getAccountGestore().getPassword());
            System.out.println(g.getCf());
            System.out.println(g.getAgenziaAppartenente().getNomeAgenzia());
            System.out.println(g.getisAdmin());
            return LoginAdminService.loginAdmin(g);
        }catch(SQLException e){
            return false;
        }
    }

}
