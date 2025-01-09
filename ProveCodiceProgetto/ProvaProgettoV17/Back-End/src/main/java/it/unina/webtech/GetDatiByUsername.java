package it.unina.webtech;

import it.unina.webtech.service.GestoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Map;

@Path("gestore/dati")
public class GetDatiByUsername {
    private GestoreService gestoreService = new GestoreService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDati(@QueryParam("username") String username) {
        if (username == null || username.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username is required")
                    .build();
        }
        try {
            Map<String, Object> gestoreData = gestoreService.getGestoreByUsername(username);
            if (gestoreData.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Gestore not found")
                        .build();
            }
            return Response.ok(gestoreData).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching data from database")
                    .build();
        }
    }
}
