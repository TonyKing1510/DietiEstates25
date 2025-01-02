package it.unina.webtech.service;
import it.unina.webtech.dao.AccountDaoImpl;
import it.unina.webtech.model.Admin;
import java.sql.SQLException;

public class LoginAdminService {
    public boolean loginAdmin(Admin admin) throws SQLException {
        try {
            AccountDaoImpl dao = new AccountDaoImpl();
            return dao.checkAdminExists(admin);
        }catch(SQLException e){
            return false;
        }
    }
}
