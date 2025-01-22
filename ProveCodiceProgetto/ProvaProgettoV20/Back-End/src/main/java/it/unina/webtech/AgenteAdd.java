package it.unina.webtech;


import it.unina.webtech.model.Agente;
import it.unina.webtech.service.AgenteService;
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
        AgenteService addAgenteService = new AgenteService();
        return addAgenteService.addAgenteService(agente);
    }

    private static boolean isCredenzialiVuote(Agente agente) {
        return agente.getCf() == null || agente.getNome() == null || agente.getCognome() == null
        || agente.getTelefono() == null ||  agente.getAccountAgente() == null || agente.getAccountAgente().getEmail() == null ||
                agente.getAccountAgente().getPassword() == null || agente.getGestoreRiferimento() == null ||
                agente.getGestoreRiferimento().getCf() == null;
    }
}
