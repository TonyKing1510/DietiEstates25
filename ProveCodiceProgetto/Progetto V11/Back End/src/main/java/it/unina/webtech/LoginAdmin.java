package it.unina.webtech;

import it.unina.webtech.model.Admin;
import it.unina.webtech.service.LoginAdminService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;

@Path("login/admin")
public class LoginAdmin {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean loginAdmin(Admin admin) throws SQLException {
        try {
            LoginAdminService loginAdminService = new LoginAdminService();
            return loginAdminService.loginAdmin(admin);
        }catch (SQLException e) {
            return false;
        }
    }

}
