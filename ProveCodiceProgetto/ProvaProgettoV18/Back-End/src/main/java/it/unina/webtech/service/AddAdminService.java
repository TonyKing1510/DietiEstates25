package it.unina.webtech.service;

import it.unina.webtech.dao.AdminDao;
import it.unina.webtech.dao.AdminDaoImpl;
import it.unina.webtech.model.Admin;

import java.sql.SQLException;

public class AddAdminService {
    public static boolean addAdmin(Admin admin) {
        try{
            if(admin.getCf() != null) {
                admin.getAccountAmministratore().setPassword(SecurityPasswordService.hashPassword(admin.getAccountAmministratore().getPassword()));
                AdminDao adminDao = new AdminDaoImpl();
                return adminDao.addAdmin(admin);
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
