package it.unina.webtech;

import it.unina.webtech.dto.ImmobileDTO;
import it.unina.webtech.model.Immobile;
import it.unina.webtech.service.ImmobileService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("immobile/add")
public class ImmobileAdd {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public static Response addImmobile(Immobile immobile){
        if(immobile==null || !isAttributiObbligatoriInseriti(immobile)){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ImmobileDTO(false,true,true))
                    .build();
        }
        ImmobileDTO dto=ImmobileService.addImmobile(immobile);
        if(isImmobileNonInserito(dto)){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ImmobileDTO(false,true,true))
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(new ImmobileDTO(true,false,false))
                .build();
    }

    private static boolean isAttributiObbligatoriInseriti(Immobile immobile) {
        if(immobile.getDettagliVendita() == null){
            return false;
        }
        return allFieldsNonNull(
                immobile.getDettagliVendita().getTipoImmobile(),
                immobile.getDettagliVendita().getTipoVendita(),
                immobile.getDescrizioneImmobile(),
                immobile.getClasseEnergetica(),
                immobile.getDettagliVendita().getPrezzo(),
                immobile.getDettagliVendita().getSpeseCondominiali(),
                immobile.getDettagliVendita().getAgenteProprietario()
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

    private static boolean isImmobileNonInserito(ImmobileDTO dto) {
        return !dto.isImmobileInserito();
    }

}
