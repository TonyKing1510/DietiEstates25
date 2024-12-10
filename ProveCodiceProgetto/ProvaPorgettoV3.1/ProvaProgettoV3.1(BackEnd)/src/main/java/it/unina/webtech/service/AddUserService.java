package it.unina.webtech.service;


import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.model.AccountSemplice;

public class AddUserService {
    public static void addUser(AccountSemplice user) {
        UserDao userDao = new UserDaoImpl();
        userDao.save(user);
    }
}
