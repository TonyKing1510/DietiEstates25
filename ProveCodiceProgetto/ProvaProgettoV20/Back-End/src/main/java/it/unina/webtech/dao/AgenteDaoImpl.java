package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.dto.AgenteCreazioneDTO;
import it.unina.webtech.dto.AgenteDatiDTO;
import it.unina.webtech.model.Agente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean addAgente(Agente agente){
        System.out.println("ciao");
        if(isEmailGiaReg(agente.getAccountAgente().getEmail())){
            System.out.println("a");
           return false;
        }
        if(isCFGiaReg(agente.getCf())){
            return false;
        }
        String sql = "insert into Agente (cf,nome,cognome,telefono,password,email,gestoreriferimento)" +
                     "values (?,?,?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, agente.getCf());
            ps.setString(2, agente.getNome());
            ps.setString(3, agente.getCognome());
            ps.setString(4, agente.getTelefono());
            ps.setString(5,agente.getAccountAgente().getPassword());
            ps.setString(6,agente.getAccountAgente().getEmail());
            ps.setString(7,agente.getGestoreRiferimento().getCf());
            int rRowsChanged=ps.executeUpdate();
            return rRowsChanged>0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AgenteDatiDTO getAgente(String email) {
        String sql = "SELECT nome, cognome, telefono, email, partitaiva, \"Bio\",cf FROM agente WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AgenteDatiDTO(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("partitaiva"),
                            rs.getString("Bio"),
                            rs.getString("cf")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
           return null;
        }
    }



    private boolean isEmailGiaReg(String email) {
        String sql = "select email from gestoreagenziaimmobiliare union select email from cliente union select email from agente";
        return esisteCampo(email, sql);
    }

    private boolean isCFGiaReg(String cf) {
        String sql = "select cf\n" +
                "from gestoreagenziaimmobiliare\n";
        return esisteCampo(cf, sql);
    }

    private boolean esisteCampo(String campo, String sql) {
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(campo)){
                    return true;
                }
                }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public String getGestoreRiferimento(String cf) {
        return "";
    }


}
