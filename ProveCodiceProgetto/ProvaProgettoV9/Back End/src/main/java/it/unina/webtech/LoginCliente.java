package it.unina.webtech;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.service.LoginClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("login")
public class LoginCliente {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(AccountSemplice account) {
        return LoginClienteService.checkIfAccountExists(account);
    }
}
