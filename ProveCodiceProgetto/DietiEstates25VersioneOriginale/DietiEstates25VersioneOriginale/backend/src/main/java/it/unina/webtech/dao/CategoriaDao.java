package it.unina.webtech.dao;

import it.unina.webtech.dto.request.UpdateCategoriaNotificaDTO;
import it.unina.webtech.dto.response.UpdateCategoriaResponse;
import it.unina.webtech.model.CategoriaNotifica;

import java.util.List;

public interface CategoriaDao {
    UpdateCategoriaResponse setInviaCategoriaCliente(String email, UpdateCategoriaNotificaDTO categoriaDaAttivare);

    UpdateCategoriaResponse setNonInviareCategoriaCliente(String cf, UpdateCategoriaNotificaDTO categoriaDaDisattivare);

    UpdateCategoriaResponse setNonInviareCategoriaAgente(String cf, UpdateCategoriaNotificaDTO categoriaDaDisattivare);

    UpdateCategoriaResponse setInviareCategoriaAgente(String cf, UpdateCategoriaNotificaDTO categoriaDaattivare);

    List<CategoriaNotifica> getCategorieDisattivateByCFAgente(String cf);

    List<CategoriaNotifica> getCategorieDisattivateByEmailCliente(String email);

    List<CategoriaNotifica> getAllCategoriaByEmail(String email);

    List<CategoriaNotifica> getAllCategoriaByCFAgente(String cf);

}
