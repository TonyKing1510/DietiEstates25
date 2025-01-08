package it.unina.webtech;

import it.unina.webtech.service.GestoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("gestore/username")
public class GetUserGestore {
    @GET
    public List<String> getUsername() throws SQLException {
        List<String> usrs= GestoreService.getAllUserNameService();
        if(usrs.isEmpty()){
            return new ArrayList<>();
        }else{
            return usrs;
        }
    }
}
