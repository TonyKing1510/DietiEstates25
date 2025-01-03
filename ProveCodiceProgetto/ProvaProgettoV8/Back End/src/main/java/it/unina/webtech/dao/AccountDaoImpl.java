package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.model.AccountSemplice;

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
}
