package it.unina.webtech.service;


import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.model.AccountSemplice;

import java.sql.SQLException;

public class AddUserService {
    public static void addUser(AccountSemplice user) {
        try {
            UserDao userDao = new UserDaoImpl();
            userDao.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
