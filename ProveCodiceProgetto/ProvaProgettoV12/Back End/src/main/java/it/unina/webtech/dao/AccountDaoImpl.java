package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {

    private Connection connection;


    public AccountDaoImpl() throws SQLException {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean checkIfExists(AccountSemplice account) throws SQLException {
            String sql = "SELECT 1 FROM Cliente c WHERE c.email = ? AND c.password = ?";
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassword());
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
            catch (SQLException e) {
            return false;
        }
        finally {
            connection.close();
        }
    }

    @Override
    public boolean checkAdminExists(Admin admin) throws SQLException {
        String sql = "SELECT 1 FROM GestoreAgenziaImmobiliare g WHERE g.nome = ? AND g.cognome = ? AND g.nomeAgenzia = ? AND g.partitaIva = ? AND g.email = ? AND g.admin = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getCognome());
            ps.setString(3, admin.getAgenziaAppartenente().getNomeAgenzia());
            ps.setString(4, admin.getPartitaIva());
            ps.setString(5, admin.getAccountAmministratore().getEmail());
            ps.setBoolean(6, admin.getisAdmin());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            return false;
        }
        finally {
            connection.close();
        }
    }
}
