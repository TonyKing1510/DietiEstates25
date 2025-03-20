package it.unina.webtech.dao;
import it.unina.webtech.dto.request.UpdateDatiRequestDTO;
import it.unina.webtech.dto.response.AgenteDTO;
import it.unina.webtech.dto.response.LoginUtenteResponse;
import it.unina.webtech.dto.response.UpdateDatiResponseDTO;

import java.sql.SQLException;

public interface UserDao {

    UpdateDatiResponseDTO updateDatiAgente(UpdateDatiRequestDTO datiAgenteNuovi, String cf);

    UpdateDatiResponseDTO updateDatiCliente(UpdateDatiRequestDTO datiNuovi,String emailAttuale);

    UpdateDatiResponseDTO updateDatiGestore(UpdateDatiRequestDTO datiNuovi, String emailAttuale);

    AgenteDTO add(String email, String password) throws SQLException;

    LoginUtenteResponse login(String email, String password) throws SQLException;

    String getPassword(String email) throws SQLException;
}
