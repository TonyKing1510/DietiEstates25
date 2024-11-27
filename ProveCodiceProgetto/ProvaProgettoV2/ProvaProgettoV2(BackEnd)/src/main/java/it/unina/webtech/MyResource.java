package it.unina.webtech;

import it.unina.webtech.model.User;
import it.unina.webtech.service.AddUserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("user")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it2!";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(User user) {

        AddUserService.addUser(user);
        return "Added it!";
    }


}
