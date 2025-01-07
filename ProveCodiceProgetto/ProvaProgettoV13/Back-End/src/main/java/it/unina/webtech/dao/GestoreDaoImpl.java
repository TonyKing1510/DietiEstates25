package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
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
    public List<String> getAllCF() throws SQLException {
        List<String> cf = new ArrayList<>();
        String sql = "SELECT cf FROM GestoreAgenziaImmobiliare g";
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
    public List<String> getAllUsername() throws SQLException {
        List<String> usernames = new ArrayList<>();
        String sql = "SELECT username FROM GestoreAgenziaImmobiliare g";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return usernames;
    }

    @Override
    public List<String> getAllEmail() throws SQLException {
        List<String> emails = new ArrayList<>();
        String sql = "SELECT email FROM GestoreAgenziaImmobiliare g";
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

    public String getAgenzia(String cf)throws SQLException{
        String agenzia = null;
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
        String sql = "insert into GestoreAgenziaImmobiliare\n" +
                "(nome,cognome,cf,telefono,via,n°civico,username,password,email,admin,gestoricreati,nomeagenzia,primoaccesso)\n" +
                "values\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, g.getNome());
            ps.setString(2, g.getCognome());
            ps.setString(3, g.getCf());
            ps.setString(4,g.getTelefono());
            ps.setString(5,g.getVia());
            ps.setString(6,g.getNumeroCivico());
            ps.setString(7,g.getAccountGestore().getUsername());
            ps.setString(8,g.getAccountGestore().getPassword());
            ps.setString(9,g.getAccountGestore().getEmail());
            ps.setBoolean(10,false);
            ps.setString(11,g.getAdminAppartenente().getCf());
            ps.setString(12,g.getAdminAppartenente().getAgenziaAppartenente().getNomeAgenzia());
            ps.setBoolean(13,false);
            int flag=ps.executeUpdate();
            return flag > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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


}
