package it.unina.webtech.dao;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GestoreDao {
     List<String> getAllCF() throws SQLException;

     List<String> getAllUsername() throws SQLException;

     List<String> getAllEmail() throws SQLException;

     boolean isAdmin(String cf,boolean isAdmin) throws SQLException;

     String getAgenzia(String cf) throws SQLException;

     boolean addGestore(GestoreAgenziaImmobiliare g) throws SQLException;

     String getSecurityPassword(String cf) throws SQLException;

     Map<String, Object> getGestoreByUsername(String username) throws SQLException;
}
