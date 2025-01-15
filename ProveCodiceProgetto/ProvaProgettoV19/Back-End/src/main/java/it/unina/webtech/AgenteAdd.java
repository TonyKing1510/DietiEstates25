package it.unina.webtech;


import it.unina.webtech.model.Agente;
import it.unina.webtech.service.AddAgenteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("agente/add")
public class AgenteAdd {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean addAgente(Agente agente) {
        if(isCredenzialiVuote(agente))
        {
            return false;
        }
        AddAgenteService addAgenteService = new AddAgenteService();
        return addAgenteService.addAgenteService(agente);
    }

    private static boolean isCredenzialiVuote(Agente agente) {
        return agente.getCf().isEmpty() || agente.getAccountAgente().getEmail().isEmpty() ||
                agente.getAccountAgente().getPassword().isEmpty()
                || agente.getNome().isEmpty() || agente.getCognome().isEmpty() || agente.getGestoreRiferimento().getCf().isEmpty();
    }
}
