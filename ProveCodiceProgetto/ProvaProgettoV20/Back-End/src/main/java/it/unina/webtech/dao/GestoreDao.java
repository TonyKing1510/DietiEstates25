package it.unina.webtech.dao;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDatiDTO;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import java.sql.SQLException;
import java.util.List;

public interface GestoreDao {
    public List<String> getAllCF() throws SQLException;

    public List<String> getAllEmail() throws SQLException;

    public boolean isAdmin(String cf,boolean isAdmin) throws SQLException;

    public String getAgenzia(String cf) throws SQLException;

    public boolean addGestore(GestoreAgenziaImmobiliare g) throws SQLException;

    public String getSecurityPassword(String cf) throws SQLException;

    public boolean updatePrimoAccesso(String cf) throws SQLException;

    public GestoreAgenziaImmobiliareDatiDTO getGestoreByEmail(String username) throws SQLException;
}
