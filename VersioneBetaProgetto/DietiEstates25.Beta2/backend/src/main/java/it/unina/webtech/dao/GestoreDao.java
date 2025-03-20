package it.unina.webtech.dao;
import it.unina.webtech.dto.request.AddGestoreRequestDTO;
import it.unina.webtech.dto.response.GestoreAgenziaImmobiliareDatiDTO;
import it.unina.webtech.dto.response.GestoreDTO;
import it.unina.webtech.dto.response.GetAllRicercheResponse;
import java.sql.SQLException;
import java.util.List;

public interface GestoreDao {
    List<String> getAllCF() throws SQLException;

    List<String> getAllEmail() throws SQLException;

    GestoreDTO addGestore(AddGestoreRequestDTO g) throws SQLException;

    String getSecurityPassword(String cf) throws SQLException;

    GestoreAgenziaImmobiliareDatiDTO getGestoreByEmail(String username) throws SQLException;

    List<GetAllRicercheResponse> getAllRicerche(String cf);

}
