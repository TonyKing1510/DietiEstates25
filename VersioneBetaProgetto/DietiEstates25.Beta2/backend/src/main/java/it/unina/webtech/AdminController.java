package it.unina.webtech;
import it.unina.webtech.dto.request.AddAdminRequestDTO;
import it.unina.webtech.dto.request.UpdatePasswordRequestDTO;
import it.unina.webtech.service.AddAdminService;
import it.unina.webtech.service.UpdatePasswordService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("admin")
public class AdminController {
    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addAdmin(AddAdminRequestDTO dto) {
        return AddAdminService.addAdmin(dto);
    }


    @Path("update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updatePassword(UpdatePasswordRequestDTO request){
        return UpdatePasswordService.updatePassword(request);
    }
}

/*lascia recensione e checkvisita per utente*/
