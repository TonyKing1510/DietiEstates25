package it.unina.webtech.dao;


import it.unina.webtech.dto.request.AddVisitaRequestDTO;
import it.unina.webtech.dto.response.*;

import java.util.List;

public interface VisitaDao {
    VisitaResponseDTO addVisita(AddVisitaRequestDTO visita);

    List<GetVisiteResponse> getAllVisiteAccettateByCF(String cf);

    List<Integer> getAllImmobileByCf(String cf);

    InformazioniVisitaDTO getInformazioniVisita(int idNotifica);

    List<DateEOreOccupateAgente> getDateEOreOccupateAgente(String cf);

    CheckVisitaPerClienteDTO controllaSeClienteHaGiaVisitaPerImmobile(String email, int idImmobile);
}
