package it.unina.webtech.service;

import it.unina.webtech.dao.AgenteDao;
import it.unina.webtech.dao.AgenteDaoImpl;
import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.dto.request.AddAgenteRequestDTO;
import it.unina.webtech.dto.request.UpdateDatiRequestDTO;
import it.unina.webtech.dto.response.AgenteCreazioneDTO;
import it.unina.webtech.dto.response.DatiDTO;
import it.unina.webtech.dto.response.GetAllRicercheResponse;
import it.unina.webtech.dto.response.UpdateDatiResponseDTO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AgenteService {
    public AgenteCreazioneDTO addAgenteService(AddAgenteRequestDTO agente){
        try {
            AgenteDao dao = new AgenteDaoImpl();
            agente.setPassword(SecurityPasswordService.hashPassword(agente.getPassword()));
            return dao.addAgente(agente);
        }
        catch (SQLException e) {
            return new AgenteCreazioneDTO(true);
        }
    }

    public static DatiDTO getAgente(String email) {
        try {
            AgenteDao dao = new AgenteDaoImpl();
            return dao.getAgenteByEmail(email);
        }catch (SQLException e) {
            return null;
        }
    }

    public static UpdateDatiResponseDTO updateService(UpdateDatiRequestDTO datiAgente, String cf){
        try {
            UserDao dao = new UserDaoImpl();
            return dao.updateDatiAgente(datiAgente,cf);
        } catch (SQLException e) {
            return new UpdateDatiResponseDTO(false,true);
        }
    }


    public static boolean updateBiografia(String newBio,String cf){
        try {
            AgenteDao dao = new AgenteDaoImpl();
            return dao.updateBiografia(newBio, cf);
        } catch (SQLException e) {
            return false;
        }
    }

    public static Integer getValutazione(String cf){
        try {
            AgenteDao dao = new AgenteDaoImpl();
            return dao.getValutazione(cf);
        }catch (SQLException e) {
            return null;
        }
    }

    public static List<GetAllRicercheResponse> getRicerche(String cf){
        try {
            AgenteDao dao = new AgenteDaoImpl();
            return dao.getAllRicerche(cf);
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }



}
