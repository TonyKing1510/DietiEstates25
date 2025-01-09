package it.unina.webtech;
import it.unina.webtech.model.Notifica;
import it.unina.webtech.service.GetNotService;
import it.unina.webtech.service.NotificationService;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;



@Path("not")
public class Notifications {
    @GET
    public List<Notifica> getAllNotifications(@QueryParam("cf") String cf) {
        List<Notifica> notifications = GetNotService.getNotificationOfAdminService(cf);
        if (notifications.isEmpty()) {
            return new ArrayList<>();
        }
        return notifications;
    }

    // Endpoint per accettare una notifica
    @PUT
    @Path("/{id}/accetta")
    @Produces("text/plain")
    public String accettaNotifica(@PathParam("id") int idNotifica) {
        // Chiamata al servizio per accettare la notifica
        NotificationService.setNotificationAccepted(idNotifica);
        return "Notifica accettata con successo!";
    }

    // Endpoint per eliminare una notifica
    @DELETE
    @Path("/{id}/elimina")
    @Produces("text/plain")
    public String eliminaNotifica(@PathParam("id") int idNotifica) {
        // Chiamata al servizio per eliminare la notifica
        NotificationService.setNotificationRejected(idNotifica);
        return "Notifica eliminata con successo!";
    }

}
