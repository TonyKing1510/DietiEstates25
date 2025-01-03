package it.unina.webtech;

import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.service.AddUserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("user")
public class MyResource{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(AccountSemplice accountSemplice) {
        AddUserService.addUser(accountSemplice);
        return "Added it!";
    }


}
