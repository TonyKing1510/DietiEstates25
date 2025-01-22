package it.unina.webtech.service;

import it.unina.webtech.dao.AdminDao;
import it.unina.webtech.dao.AdminDaoImpl;
import it.unina.webtech.model.Admin;

import java.sql.SQLException;

public class AddAdminService {
    public static boolean addAdmin(Admin admin) {
        try{
            admin.getAccountAmministratore().setPassword(SecurityPasswordService.hashPassword(admin.getAccountAmministratore().getPassword()));
            AdminDao adminDao = new AdminDaoImpl();
            return adminDao.addAdmin(admin);
        } catch (SQLException e) {
            return false;
        }
    }
}
