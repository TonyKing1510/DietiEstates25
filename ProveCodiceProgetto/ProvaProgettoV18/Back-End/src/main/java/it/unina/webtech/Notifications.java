package it.unina.webtech;
import it.unina.webtech.dto.NotificaDTO;
import it.unina.webtech.model.Notifica;
import it.unina.webtech.service.NotificationsService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;



@Path("not")
public class Notifications {
    @GET
    public List<Notifica> getAllNotifications(@QueryParam("cf") String cf) {
        List<Notifica> notifications = NotificationsService.getNotificationOfAdminService(cf);
        if (notifications.isEmpty()) {
            return new ArrayList<>();
        }
        return notifications;
    }

    @PUT
    @Path("/{id}/accetta")
    @Produces(MediaType.APPLICATION_JSON)
    public NotificaDTO accettaNotifica(@PathParam("id") int idNotifica) {
        return NotificationsService.setNotificationAccepted(idNotifica);
    }


    @DELETE
    @Path("/{id}/elimina")
    @Produces(MediaType.APPLICATION_JSON)
    public NotificaDTO eliminaNotifica(@PathParam("id") int idNotifica) {
        return NotificationsService.setNotificationAccepted(idNotifica);
    }

}
