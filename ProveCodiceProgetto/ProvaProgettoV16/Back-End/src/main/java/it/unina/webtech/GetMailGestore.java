package it.unina.webtech;

import it.unina.webtech.service.GestoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("gestore/email")
public class GetMailGestore {
    @GET
    public List<String> getMail() throws SQLException {
        List<String> mails= GestoreService.getAllEmailService();
        if(mails.isEmpty()){
            return new ArrayList<>();
        }else{
            return mails;
        }
    }
}
