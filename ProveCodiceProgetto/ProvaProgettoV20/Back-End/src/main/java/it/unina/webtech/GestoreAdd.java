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
        if(campiNonInseriti(gestore)) {
            return false;
        }
        return AddGestoreService.addGestore(gestore);
        }

    private static boolean campiNonInseriti(GestoreAgenziaImmobiliare gestore) {
        return allFieldsNonNull(
                gestore.getCf()
                ,gestore.getAccountGestore().getEmail(),
                gestore.getAccountGestore().getPassword(),
                gestore.getNome(), gestore.getCognome(),gestore.getTelefono(),
                gestore.getAdminAppartenente(),
                gestore.getAdminAppartenente().getCf(),gestore.getAgenziaAppartenente(),
                gestore.getAdminAppartenente().getAgenziaAppartenente(),
                gestore.getAdminAppartenente().getAgenziaAppartenente().getNomeAgenzia()
        );
    }

    private static boolean allFieldsNonNull(Object... fields) {
        for (Object field : fields) {
            if (field == null) {
                return false;
            }
        }
        return true;
    }
}
