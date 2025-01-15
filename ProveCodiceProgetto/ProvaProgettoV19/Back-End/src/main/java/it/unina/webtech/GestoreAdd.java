package it.unina.webtech;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.AddGestoreService;
import it.unina.webtech.service.GestoreService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;


@Path("gestore/add")
public class GestoreAdd {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addGestore(GestoreAgenziaImmobiliare gestore){
        if(gestore.getCf().isEmpty() || gestore.getAccountGestore().getEmail().isEmpty() || gestore.getAccountGestore().getPassword().isEmpty()) {
            return false;
        }
        return AddGestoreService.addGestore(gestore);
        }
    }
