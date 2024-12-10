package it.unina.webtech;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.service.LoginClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("login")
public class Login {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(AccountSemplice account) {
        if(LoginClienteService.checkIfAccountExists(account)){
                return "Login !";
        }
        return "Login Error";
    }
}
