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
        try{
            String sql = "Select email,password from Cliente c where c.email = ? and c.password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
        finally {
            connection.close();
        }
    }
}
