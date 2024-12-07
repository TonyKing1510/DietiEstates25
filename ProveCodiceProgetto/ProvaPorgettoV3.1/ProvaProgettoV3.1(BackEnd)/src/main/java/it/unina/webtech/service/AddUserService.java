package it.unina.webtech.service;


import it.unina.webtech.model.User;
import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;

public class AddUserService {
    public static void addUser(User user) {
        UserDao userDao = new UserDaoImpl();
        userDao.save(user);
    }
}
