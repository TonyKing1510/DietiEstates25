package it.unina.webtech;

import it.unina.webtech.model.Admin;

import it.unina.webtech.service.AddAdminService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("admin/add")
public class AdminAdd {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addAdmin(Admin a){
        if(isCredenzialiInserite(a)){
            return AddAdminService.addAdmin(a);
        }
        return false;
    }

    private static boolean isCredenzialiInserite(Admin a) {
        return isCfInserito(a.getCf()) && isCredenzialiAccountInserite(a.getAccountAmministratore().getEmail()
                , a.getAccountAmministratore().getPassword(), a.getAccountAmministratore().getUsername());
    }

    private static boolean isCredenzialiAccountInserite(String email,String password,String username) {
        return !email.isEmpty() && !password.isEmpty() && !username.isEmpty();
    }

    private static boolean isCfInserito(String a) {
        return !a.isEmpty();
    }
}
