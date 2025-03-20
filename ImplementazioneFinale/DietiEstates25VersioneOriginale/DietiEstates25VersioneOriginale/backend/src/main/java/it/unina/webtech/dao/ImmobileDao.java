package it.unina.webtech.dao;

import it.unina.webtech.dto.request.AddImmobileRequestDTO;
import it.unina.webtech.dto.request.RicercaImmobileDTORequest;
import it.unina.webtech.dto.response.DatiImmobileDTO;
import it.unina.webtech.dto.response.ImmobileDTO;
import it.unina.webtech.dto.response.ImmobileResponseRicercaDTO;
import it.unina.webtech.model.Immobile;
import java.util.List;

public interface ImmobileDao {
    ImmobileDTO addImmobile(AddImmobileRequestDTO immobile);

    boolean deleteImmobile(int idImmobile);

    List<Immobile> getAnnunciByEmail(String email);

    List<ImmobileResponseRicercaDTO> getImmobiliRicerca(RicercaImmobileDTORequest ricerca);

    DatiImmobileDTO getInfoAboutImmobile(int idImmobile);

    List<ImmobileResponseRicercaDTO> getImmobiliForAgente(String mail);

    ImmobileResponseRicercaDTO getInfoAboutImmobileById(int idImmobile);

}
