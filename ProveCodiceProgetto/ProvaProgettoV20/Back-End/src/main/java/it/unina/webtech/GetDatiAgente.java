package it.unina.webtech;

import it.unina.webtech.dto.AgenteDatiDTO;
import it.unina.webtech.service.AgenteService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("agente/dati")
public class GetDatiAgente {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDatiAgente(@QueryParam("email") String email) {
        if (email == null || email.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email is required")
                    .build();
        }
        AgenteDatiDTO dto = AgenteService.getAgente(email);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agente not found")
                    .build();
        }
        return Response.status(Response.Status.OK).entity(dto).build();

    }
}
