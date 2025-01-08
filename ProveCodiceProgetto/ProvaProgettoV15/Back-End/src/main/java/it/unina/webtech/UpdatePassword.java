package it.unina.webtech;

import it.unina.webtech.model.Admin;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.UpdatePasswordService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("admin/update")
public class UpdatePassword {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updatePassword(GestoreAgenziaImmobiliare gestore){
        return UpdatePasswordService.updatePassword(gestore);
    }
}
