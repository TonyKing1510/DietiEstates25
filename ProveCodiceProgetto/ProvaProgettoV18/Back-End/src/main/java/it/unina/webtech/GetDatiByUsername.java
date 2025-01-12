package it.unina.webtech;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDatiDTO;
import it.unina.webtech.service.GestoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;


@Path("gestore/dati")
public class GetDatiByUsername {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDati(@QueryParam("username") String username) {
        if (username == null || username.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username is required")
                    .build();
        }
        try {
            GestoreAgenziaImmobiliareDatiDTO dto = GestoreService.getGestoreByUsername(username);
            if (dto == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Gestore not found")
                        .build();
            }
            return Response
                    .status(Response.Status.OK)
                    .entity(dto)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching data from database")
                    .build();
        }
    }
}