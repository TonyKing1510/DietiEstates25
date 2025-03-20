package it.unina.webtech.dao;
import it.unina.webtech.dto.request.AddGestoreRequestDTO;
import it.unina.webtech.dto.response.*;

import java.sql.SQLException;
import java.util.List;

public interface GestoreDao {
    List<String> getAllCF() throws SQLException;

    List<String> getAllEmail() throws SQLException;

    GestoreDTO addGestore(AddGestoreRequestDTO g) throws SQLException;

    String getSecurityPassword(String cf) throws SQLException;

    GestoreAgenziaImmobiliareDatiDTO getGestoreByEmail(String username) throws SQLException;

    List<GetAllRicercheResponse> getAllRicerche(String cf);

    List<DatiDTO> getAllAccountAgenti(String cf);

    List<DatiDTO> getAllAccountGestori(String cf);

    boolean deleteGestoreByEmail(String email);

    boolean deleteAgenteByEmail(String email);

}
