package it.unina.webtech.dao;


import it.unina.webtech.model.AccountSemplice;

public interface UserDao {
    void save(AccountSemplice user);
}
