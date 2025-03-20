package it.unina.webtech.dao;

import it.unina.webtech.dto.response.FotoDTO;
import java.util.List;

public interface ImageDao {
    FotoDTO addImage(String assertId, String cf);

    List<String> getPublicIdByCf(String cf);

    List<String> getImageOfImmobile(int idImmobile);
}
