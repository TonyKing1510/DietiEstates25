package it.unina.webtech;
import it.unina.webtech.dto.request.*;
import it.unina.webtech.dto.response.AgenteCreazioneDTO;
import it.unina.webtech.dto.response.DatiDTO;
import it.unina.webtech.dto.response.GetAllRicercheResponse;
import it.unina.webtech.dto.response.UpdateDatiResponseDTO;
import it.unina.webtech.filter.RequireJWTAuthentication;
import it.unina.webtech.service.AgenteService;
import it.unina.webtech.service.UpdatePasswordService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static it.unina.webtech.GestoreController.validaInput;

@Path("agente")
public class AgenteController {
    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RequireJWTAuthentication
    public Response addAgente(AddAgenteRequestDTO agente) {
        String jsonResponse=validaInput(agente);
        if(inputNonValido(jsonResponse)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonResponse).build();
        }
        AgenteService addAgenteService = new AgenteService();
        AgenteCreazioneDTO dto=addAgenteService.addAgenteService(agente);
        if(isAgenteDuplicato(dto)){return Response.status(Response.Status.CONFLICT).entity(dto).build();}
        if(isVerificatoErroreInterno(dto)){return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dto).build();}
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    private static boolean isVerificatoErroreInterno(AgenteCreazioneDTO dto) {
        return dto.isErrore();
    }

    private static boolean isAgenteDuplicato(AgenteCreazioneDTO dto) {
        return dto.isCfGiàRegistrato() || dto.isEmailGiàRegistrata();
    }

    private static boolean inputNonValido(String jsonResponse) {
        return jsonResponse != null;
    }


    @Path("dati")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequireJWTAuthentication
    public Response fetchDatiAgente(@QueryParam("email") String email) {
        if (email == null || email.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email is required")
                    .build();
        }
        DatiDTO dto = AgenteService.getAgente(email);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agente not found")
                    .build();
        }
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @Path("updatedati")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequireJWTAuthentication
    public Response updateDati(@QueryParam("cf") String cf, UpdateDatiRequestDTO dto) {
        String jsonResponse=validaInput(dto);
        if(inputNonValido(jsonResponse)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonResponse).build();
        }
        if (validaCf(cf)) return Response.status(Response.Status.BAD_REQUEST).build();
        UpdateDatiResponseDTO response=AgenteService.updateService(dto,cf);
        if(response.getEmailDuplicate()){
            return Response.status(Response.Status.CONFLICT).entity(response).build();
        }
        if(response.getError()){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }

    static boolean validaCf(String cf) {
        return cf == null || cf.isEmpty();
    }

    @Path("updateBio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequireJWTAuthentication
    public Response updateBio(@QueryParam("cf") String cf, UpdateBiografiaRequestDTO request) {
        String jsonResponse=validaInput(request);
        if(inputNonValido(jsonResponse)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonResponse).build();
        }
        if (validaCf(cf)) return Response.status(Response.Status.BAD_REQUEST).build();
        boolean response=AgenteService.updateBiografia(request.getBiografia(),cf);
        return gestisciRisposta(response);
    }

    private static Response gestisciRisposta(boolean response) {
        if(response){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("valutazione")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequireJWTAuthentication
    public Response getValutazione(@QueryParam("cf")String cf){
        if(cf == null || cf.length() < 16){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Integer valutazione=AgenteService.getValutazione(cf);
        if(valutazione==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(valutazione).build();
    }

    @Path("ricerche/{cf}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRicerhce(@PathParam("cf")String cf){
        if(validaCf(cf)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<GetAllRicercheResponse> ricerche=AgenteService.getRicerche(cf);
        if(ricerche==null || ricerche.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(ricerche).build();
    }

    @Path("updatePassword/agente")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updatePassword(UpdatePasswordRequestDTO request){
        return UpdatePasswordService.updatePasswordAgente(request);
    }
}
