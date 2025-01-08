package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.Admin;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {

    private Connection connection;


    public AdminDaoImpl() throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        String sql = "insert into gestoreagenziaimmobiliare" +
                "(nome,cognome,cf,telefono,via,n°civico,username,password,email,admin,nomeagenzia,isfattoprimoaccesso)"+
                " values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getCognome());
            ps.setString(3, admin.getCf());
            ps.setString(4, admin.getTelefono());
            ps.setString(5, admin.getVia());
            ps.setString(6,admin.getNumeroCivico());
            ps.setString(7,admin.getAccountAmministratore().getUsername());
            ps.setString(8,admin.getAccountAmministratore().getPassword());
            ps.setString(9,admin.getAccountAmministratore().getEmail());
            ps.setBoolean(10,true);
            ps.setString(11,admin.getAgenziaAppartenente().getNomeAgenzia());
            ps.setBoolean(12,false);
            int rows=ps.executeUpdate();
            return rows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(GestoreAgenziaImmobiliare gestore) throws SQLException {
        String sql = "Update gestoreagenziaimmobiliare set password=? where cf=?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,gestore.getAccountGestore().getPassword());
            ps.setString(2, gestore.getCf());
            int rows=ps.executeUpdate();
            return rows > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
