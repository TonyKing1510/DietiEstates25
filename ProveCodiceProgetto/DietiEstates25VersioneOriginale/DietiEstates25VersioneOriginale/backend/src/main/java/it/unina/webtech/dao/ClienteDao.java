package it.unina.webtech.dao;
import it.unina.webtech.dto.request.AddClienteRequestDTO;
import it.unina.webtech.dto.request.LasciaRecensioneRequestDTO;
import it.unina.webtech.dto.request.UpdatePasswordRequestDTO;
import it.unina.webtech.dto.response.ClienteDatiDTO;
import it.unina.webtech.dto.response.ClienteDTO;
import it.unina.webtech.dto.response.GetAllRicercheResponse;

import java.util.List;

public interface ClienteDao {
    ClienteDTO insert(AddClienteRequestDTO cliente);

    boolean exists(AddClienteRequestDTO cliente);

    ClienteDatiDTO getClienteByEmail(String email);

    boolean lasciaRecensione(LasciaRecensioneRequestDTO request);

    List<GetAllRicercheResponse> getAllRicerche(String email);

    boolean updatePassword(UpdatePasswordRequestDTO request);
}
