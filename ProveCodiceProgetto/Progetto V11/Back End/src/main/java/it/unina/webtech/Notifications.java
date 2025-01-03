package it.unina.webtech;
import it.unina.webtech.model.Notifica;
import it.unina.webtech.service.GetNotService;
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
}
