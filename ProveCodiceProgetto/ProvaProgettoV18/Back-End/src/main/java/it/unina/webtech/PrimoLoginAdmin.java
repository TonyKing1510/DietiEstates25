package it.unina.webtech;

import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.PrimoLoginAdminService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;

@Path("login/admin")
public class PrimoLoginAdmin {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public GestoreAgenziaImmobiliareDTO loginAdmin(GestoreAgenziaImmobiliare g){
        try {
            GestoreAgenziaImmobiliareDTO dto=PrimoLoginAdminService.loginAdmin(g);
            PrimoLoginAdminService.updatePrimoAdmin(g);
            return dto;
        }catch(SQLException e){
            return null;
        }
    }

}
