package it.unina.webtech;
import it.unina.webtech.dao.NotDaoImpl;
import it.unina.webtech.model.Notifica;
import it.unina.webtech.service.GetNotService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;



@Path("not")
public class Not {
    // Istanzia il servizio per le notifiche
    private final GetNotService notificationService = new GetNotService(new NotDaoImpl());

    @GET
    @Produces(MediaType.APPLICATION_JSON)  // Rispondi con JSON
    public Response getAllNotifications() {
        // Ottieni tutte le notifiche
        List<Notifica> notifications = notificationService.getNot();
        // Se ci sono notifiche, restituisci il JSON, altrimenti rispondi con errore 404
        if (notifications.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No notifications found")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity(notifications)  // Restituisci la lista delle notifiche in formato JSON
                .build();
    }
}
