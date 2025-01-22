package it.unina.webtech.dao;
import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDatiDTO;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestoreDaoImpl implements GestoreDao{
    private Connection connection;

    public GestoreDaoImpl() throws SQLException{
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllCF()  {
        List<String> cf = new ArrayList<>();
        String sql = "SELECT cf FROM GestoreAgenziaImmobiliare g union SELECT cf from agente UNION select email from cliente";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cf.add(rs.getString("cf"));
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return cf;
    }

    @Override
    public List<String> getAllEmail() {
        List<String> emails = new ArrayList<>();
        String sql = "SELECT email FROM GestoreAgenziaImmobiliare g union select email from agente union select email from cliente";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return emails;
    }

    @Override
    public boolean isAdmin(String cf,boolean isAdmin) throws SQLException {
        String sql = "select 1 from GestoreAgenziaImmobiliare where cf = ? and isAdmin = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cf);
            ps.setBoolean(2, isAdmin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            return false;
        }
        return false;
    }

    public String getAgenzia(String cf){
        String agenzia;
        String sql = "select g.nomeagenzia from GestoreAgenziaImmobiliare g  where cf = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                agenzia = rs.getString("nomeagenzia");
                return agenzia;
            }
            return null;
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addGestore(GestoreAgenziaImmobiliare g) throws SQLException {
        if(isCFGiaReg(g.getCf())){
            return false;
        }
        if(isEmailGiaReg(g.getAccountGestore().getEmail())){
            return false;
        }
        String sql = "insert into GestoreAgenziaImmobiliare\n" +
                "(nome,cognome,cf,telefono,password,email,admin,gestoricreati,nomeagenzia,isfattoprimoaccesso)\n" +
                "values\n" +
                "(?,?,?,?,?,?,?,?,?,?)";
        try( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, g.getNome());
            ps.setString(2, g.getCognome());
            ps.setString(3, g.getCf());
            ps.setString(4,g.getTelefono());
            ps.setString(5,g.getAccountGestore().getPassword());
            ps.setString(6,g.getAccountGestore().getEmail());
            ps.setBoolean(7,false);
            ps.setString(8,g.getAdminAppartenente().getCf());
            ps.setString(9,g.getAdminAppartenente().getAgenziaAppartenente().getNomeAgenzia());
            ps.setBoolean(10,false);
            int flag=ps.executeUpdate();
            return flag > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isCFGiaReg(String cf){
        List<String> list = getAllCF();
        for (String s:list){
            if(s.equals(cf)){
                return true;
            }
        }
        return false;
    }

    private boolean isEmailGiaReg(String email){
        List<String> list = getAllEmail();
        for (String s:list){
            if(s.equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSecurityPassword(String cf) throws SQLException {
        String sql = "select g.password from gestoreagenziaimmobiliare g where cf = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean updatePrimoAccesso(String cf) throws SQLException {
        String sql = "update gestoreagenziaimmobiliare set isfattoPrimoAccesso = true where cf = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cf);
            int nRows=ps.executeUpdate();
            return nRows>0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GestoreAgenziaImmobiliareDatiDTO getGestoreByEmail(String email) throws SQLException {
        String sql = "SELECT nome, cognome, cf, telefono, email, partitaiva, username FROM gestoreagenziaimmobiliare WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GestoreAgenziaImmobiliareDatiDTO dto = new GestoreAgenziaImmobiliareDatiDTO();
                dto.setNome((rs.getString("nome")));
                dto.setCognome(rs.getString("cognome"));
                dto.setCf(rs.getString("cf"));
                dto.setTelefono(rs.getString("telefono"));
                dto.setEmail(rs.getString("email"));
                dto.setPartitaIva(rs.getString("partitaiva"));
                dto.setUsername(rs.getString("username"));
                return dto;
            }else{
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }


}
