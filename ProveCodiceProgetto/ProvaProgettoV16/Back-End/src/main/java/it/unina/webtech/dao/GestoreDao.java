package it.unina.webtech.dao;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface GestoreDao {
    public List<String> getAllCF() throws SQLException;

    public List<String> getAllUsername() throws SQLException;

    public List<String> getAllEmail() throws SQLException;

    public boolean isAdmin(String cf,boolean isAdmin) throws SQLException;

    public String getAgenzia(String cf) throws SQLException;

    public boolean addGestore(GestoreAgenziaImmobiliare g) throws SQLException;

    public String getSecurityPassword(String cf) throws SQLException;

    public Map<String, Object> getGestoreByUsername(String username) throws SQLException;
}
