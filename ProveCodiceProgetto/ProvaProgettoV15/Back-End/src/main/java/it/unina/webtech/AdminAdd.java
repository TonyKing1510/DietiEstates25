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
        return AddAdminService.addAdmin(a);
    }
}
