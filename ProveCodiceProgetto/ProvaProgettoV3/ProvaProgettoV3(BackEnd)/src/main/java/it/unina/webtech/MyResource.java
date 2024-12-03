package it.unina.webtech;

import it.unina.webtech.dao.NotDaoImpl;
import it.unina.webtech.model.Notification;
import it.unina.webtech.model.User;
import it.unina.webtech.service.AddUserService;
import it.unina.webtech.service.GetNotService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("user")
public class MyResource{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(User user) {

        AddUserService.addUser(user);
        return "Added it!";
    }


}
