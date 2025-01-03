package it.unina.webtech;
import it.unina.webtech.model.Notifica;
import it.unina.webtech.service.GetNotService;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;



@Path("not")
public class Notifications {
    @GET
    public List<Notifica> getAllNotifications(@QueryParam("partitaIva") String partitaIva) {
        List<Notifica> notifications = GetNotService.getNotificationOfAdminService(partitaIva);
        if (notifications.isEmpty()) {
            return new ArrayList<>();
        }
        return notifications;
    }
}
