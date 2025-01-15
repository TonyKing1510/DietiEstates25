package it.unina.webtech;
import it.unina.webtech.dto.ClienteDTO;
import it.unina.webtech.model.Cliente;
import it.unina.webtech.service.ClienteService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.TimeUnit;

@Path("cliente/add")
public class ClienteAdd {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCliente(Cliente cliente) {
        if(isCredenzialiNonInserite(cliente)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        ClienteDTO dto = ClienteService.addCliente(cliente);
        if(isVerificatoErroreInterno(dto)){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        if(isClienteEsiste(dto)){
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ClienteDTO(null,true,false))
                    .build();
        }else{
            String token = AuthController.createJWT(cliente.getAccountCliente().getUsername(), TimeUnit.DAYS.toMillis(365));
            return Response
                    .status(Response.Status.OK)
                    .entity(new ClienteDTO(token,false,false))
                    .build();
        }
    }

    private static boolean isCredenzialiNonInserite(Cliente cliente) {
        return cliente.getAccountCliente() == null || cliente.getNome() == null || cliente.getCognome() == null
                ||cliente.getTelefono() == null || cliente.getAccountCliente().getPassword() == null;
    }

    private static boolean isVerificatoErroreInterno(ClienteDTO dto) {
        return dto.isErroreInterno();
    }

    private static boolean isClienteEsiste(ClienteDTO dto) {
        return dto.isDuplicato();
    }
}
