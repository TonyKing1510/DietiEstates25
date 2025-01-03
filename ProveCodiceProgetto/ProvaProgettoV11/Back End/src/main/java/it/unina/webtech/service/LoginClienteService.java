package it.unina.webtech.service;

import it.unina.webtech.dao.AccountDao;
import it.unina.webtech.dao.AccountDaoImpl;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.model.AccountSemplice;

import java.sql.SQLException;

public class LoginClienteService {
    public static boolean checkIfAccountExists(AccountSemplice account){
        try {
            AccountDao dao = new AccountDaoImpl();
            return dao.checkIfExists(account);
        }catch(SQLException e){
            return false;
        }
    }
}
