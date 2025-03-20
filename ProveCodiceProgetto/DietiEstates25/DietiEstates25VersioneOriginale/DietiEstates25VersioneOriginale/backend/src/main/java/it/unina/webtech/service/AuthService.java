package it.unina.webtech.service;
import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.dto.response.LoginUtenteResponse;

import java.sql.SQLException;

public class AuthService {

    private AuthService(){}


    public static LoginUtenteResponse login(String email, String password){
        try {
            UserDao dao = new UserDaoImpl();
            String passwordStored=dao.getPassword(email);
            if(SecurityPasswordService.checkPassword(password,passwordStored)) {
                return dao.login(email, password);
            }
            return null;
        }catch (SQLException e){
            return null;
        }
    }
}
