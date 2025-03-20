package it.unina.webtech.service;

import it.unina.webtech.dao.AdminDao;
import it.unina.webtech.dao.AdminDaoImpl;
import it.unina.webtech.dto.request.AddAdminRequestDTO;
import java.sql.SQLException;

public class AddAdminService {

    private AddAdminService(){}

    public static boolean addAdmin(AddAdminRequestDTO admin) {
        try{
            admin.setPassword(SecurityPasswordService.hashPassword(admin.getPassword()));
            AdminDao adminDao = new AdminDaoImpl();
            return adminDao.addAdmin(admin);
        } catch (SQLException e) {
            return false;
        }
    }
}
