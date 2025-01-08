package it.unina.webtech;


import it.unina.webtech.service.GestoreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("gestore/cf")
public class GetCFGestore {
    @GET
    public List<String> getCf() throws SQLException {
        List<String> cf=GestoreService.getAllCfService();
        if(cf.isEmpty()){
            return new ArrayList<>();
        }else{
            return cf;
        }
    }
}
