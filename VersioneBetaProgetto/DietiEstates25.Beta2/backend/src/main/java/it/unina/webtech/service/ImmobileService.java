package it.unina.webtech.service;
import it.unina.webtech.dao.ImmobileDao;
import it.unina.webtech.dao.ImmobileDaoImpl;
import it.unina.webtech.dto.request.AddImmobileRequestDTO;
import it.unina.webtech.dto.request.RicercaImmobileDTORequest;
import it.unina.webtech.dto.response.DatiImmobileDTO;
import it.unina.webtech.dto.response.ImmobileDTO;
import it.unina.webtech.dto.response.ImmobileResponseRicercaDTO;
import it.unina.webtech.model.Immobile;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ImmobileService {

    private ImmobileService(){}


    public static ImmobileDTO addImmobile(AddImmobileRequestDTO immobile){
        try{
            ImmobileDao immobileDao = new ImmobileDaoImpl();
            return immobileDao.addImmobile(immobile);
        } catch (SQLException e) {
           return new ImmobileDTO(false,true,true);
        }
    }

    public static List<Immobile> getAllAnnunci(String email) {
        try {
            ImmobileDao dao = new ImmobileDaoImpl();
            return dao.getAnnunciByEmail(email);
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public static List<ImmobileResponseRicercaDTO> getImmobile(RicercaImmobileDTORequest request){
        try {
            ImmobileDao dao = new ImmobileDaoImpl();
            return dao.getImmobiliRicerca(request);
        }catch (SQLException e) {
            return Collections.emptyList();
        }
    }


    public static DatiImmobileDTO getInfoAboutImmobile(int id){
        try {
            ImmobileDao dao = new ImmobileDaoImpl();
            return dao.getInfoAboutImmobile(id);
        }catch (SQLException e) {
            return null;
        }
    }

    public static List<ImmobileResponseRicercaDTO> getImmobileForAgente(String cf){
        try{
            ImmobileDao dao = new ImmobileDaoImpl();
            return dao.getImmobiliForAgente(cf);
        }catch (SQLException e) {
            return Collections.emptyList();
        }
    }

}

