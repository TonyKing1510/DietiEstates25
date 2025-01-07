package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.Agente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgenteDaoImpl implements AgenteDao {
    private Connection connection;

    public AgenteDaoImpl() throws SQLException {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addAgente(Agente agente) throws SQLException {
        String sql = "insert into Agente (cf,nome,cognome,telefono,via,numerocivico,username,password,email,gestoreriferimento)" +
                     "values (?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, agente.getCf());
            ps.setString(2, agente.getNome());
            ps.setString(3, agente.getCognome());
            ps.setString(4, agente.getTelefono());
            ps.setString(5, agente.getVia());
            ps.setString(6, agente.getNumeroCivico());
            ps.setString(7,agente.getAccountAgente().getUsername());
            ps.setString(8,agente.getAccountAgente().getPassword());
            ps.setString(9,agente.getAccountAgente().getEmail());
            ps.setString(10,agente.getGestoreRiferimento().getCf());
            int rRowsChanged=ps.executeUpdate();
            return rRowsChanged>0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Agente getAgente(String cf) {
        return null;
    }

    @Override
    public String getGestoreRiferimento(String cf) {
        return "";
    }


}
