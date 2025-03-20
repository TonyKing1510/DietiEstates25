package it.unina.webtech.service;
import it.unina.webtech.dao.VisitaDao;
import it.unina.webtech.dao.VisitaDaoImpl;
import it.unina.webtech.dto.request.AddAdminRequestDTO;
import it.unina.webtech.dto.request.AddVisitaRequestDTO;
import it.unina.webtech.dto.response.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VisitaService {
    private VisitaService(){}

    public static VisitaResponseDTO addVisita(AddVisitaRequestDTO visita){
        try {
            if(isDataFineDopoDataInizio(visita)){
                return new VisitaResponseDTO(false,true,false,false,true);
            }
            VisitaDao dao = new VisitaDaoImpl();
            return dao.addVisita(visita);
        }catch (SQLException e){
            return new VisitaResponseDTO(false,true);
        }
    }

    private static boolean isDataFineDopoDataInizio(AddVisitaRequestDTO visita) {
        return visita.getHoraFineVisita().isBefore(visita.getHoraInizioVisita());
    }

    public static List<GetVisiteResponse> getAllVisiteByCf(String cf){
        try {
            VisitaDao dao = new VisitaDaoImpl();
            return dao.getAllVisiteAccettateByCF(cf);
        }catch (SQLException e){
            return Collections.emptyList();
        }
    }

    public static InformazioniVisitaDTO getInfoAboutVisita(int idNotifica){
        try {
            VisitaDao dao = new VisitaDaoImpl();
            return dao.getInformazioniVisita(idNotifica);
        } catch (SQLException e) {
            return null;
        }
    }

    public static List<DateEOreOccupateAgente> getDateEOreOccupateAgente(String cf){
        try {
            VisitaDao dao = new VisitaDaoImpl();
            return dao.getDateEOreOccupateAgente(cf);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public static CheckVisitaPerClienteDTO checkUtenteHaVisitaPerImmobile(String email, int idImmobile){
        try {
            VisitaDao dao = new VisitaDaoImpl();
            return dao.controllaSeClienteHaGiaVisitaPerImmobile(email, idImmobile);
        }catch (SQLException e){
            return new CheckVisitaPerClienteDTO(true,false,false);
        }
    }

    public static boolean deleteVisita(AddVisitaRequestDTO visita){
        try {
            VisitaDao dao = new VisitaDaoImpl();
            return dao.eliminaVisita(visita);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
