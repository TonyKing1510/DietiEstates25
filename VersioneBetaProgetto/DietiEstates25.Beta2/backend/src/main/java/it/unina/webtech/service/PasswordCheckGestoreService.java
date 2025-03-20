package it.unina.webtech.service;

import it.unina.webtech.dao.GestoreDao;
import it.unina.webtech.dao.GestoreDaoImpl;
import it.unina.webtech.dto.request.CheckPasswordRequestDTO;

import java.sql.SQLException;

public class PasswordCheckGestoreService {

    private PasswordCheckGestoreService(){}

    public static boolean checkPassword(CheckPasswordRequestDTO checkPasswordRequestDTO) {
        try {
            GestoreDao dao = new GestoreDaoImpl();
            String passwordhash=dao.getSecurityPassword(checkPasswordRequestDTO.getEmail());
            if(passwordhash == null){
                return false;
            }
            return SecurityPasswordService.checkPassword(checkPasswordRequestDTO.getPassword(),passwordhash);
        } catch (SQLException e) {
            return false;
        }

    }
}
