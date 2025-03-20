package it.unina.webtech.service;
import it.unina.webtech.dao.*;
import it.unina.webtech.dto.request.UpdateDatiRequestDTO;
import it.unina.webtech.dto.response.DatiDTO;
import it.unina.webtech.dto.response.GestoreAgenziaImmobiliareDatiDTO;
import it.unina.webtech.dto.response.GetAllRicercheResponse;
import it.unina.webtech.dto.response.UpdateDatiResponseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestoreService {

    private GestoreService(){}

    public static List<String> getAllCfService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllCF();
    }


    public static List<String> getAllEmailService() throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getAllEmail();
    }

    public static GestoreAgenziaImmobiliareDatiDTO getGestoreByEmail(String email) throws SQLException {
        GestoreDao dao = new GestoreDaoImpl();
        return dao.getGestoreByEmail(email);
    }

    public static List<GetAllRicercheResponse> getRicerche(String cf){
        try {
             GestoreDao dao = new GestoreDaoImpl();
            return dao.getAllRicerche(cf);
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public static UpdateDatiResponseDTO updateDati(UpdateDatiRequestDTO nuoviDati, String emailAttuale) {
        try {
            UserDao dao = new UserDaoImpl();
            return dao.updateDatiGestore(nuoviDati,emailAttuale);
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean deleteAgenteByEmail(String email){
        try{
            GestoreDao dao = new GestoreDaoImpl();
            return dao.deleteAgenteByEmail(email);
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean deleteGestoreByEmail(String email){
        try{
            GestoreDao dao = new GestoreDaoImpl();
            return dao.deleteGestoreByEmail(email);
        }catch (SQLException e){
            return false;
        }
    }

    public static List<DatiDTO> getAccountGestori(String cf){
        try{
            GestoreDao dao = new GestoreDaoImpl();
            return dao.getAllAccountGestori(cf);
        }catch (SQLException e){
            return new ArrayList<>();
        }
    }
}
