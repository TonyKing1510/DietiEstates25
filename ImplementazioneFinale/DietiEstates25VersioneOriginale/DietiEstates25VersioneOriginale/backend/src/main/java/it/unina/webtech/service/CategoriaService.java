package it.unina.webtech.service;
import it.unina.webtech.dao.CategoriaDao;
import it.unina.webtech.dao.CategoriaDaoImpl;
import it.unina.webtech.dto.request.UpdateCategoriaNotificaDTO;
import it.unina.webtech.dto.response.UpdateCategoriaResponse;
import it.unina.webtech.model.CategoriaNotifica;

import java.util.List;

public class CategoriaService {

    private CategoriaService(){}

    public static UpdateCategoriaResponse setInviaCategoriaAdCliente(String email, UpdateCategoriaNotificaDTO categoriaNotifica){
        CategoriaDao notDao = new CategoriaDaoImpl();
        return notDao.setInviaCategoriaCliente(email, categoriaNotifica);
    }


    public static UpdateCategoriaResponse setNonInviareCategoriaAdCliente(String email, UpdateCategoriaNotificaDTO categoriaNotifica){
        CategoriaDao notDao = new CategoriaDaoImpl();
        return notDao.setNonInviareCategoriaCliente(email, categoriaNotifica);
    }

    public static UpdateCategoriaResponse setNonInviareCategoriaAdAgente(String cf, UpdateCategoriaNotificaDTO categoriaNotifica){
        CategoriaDao notDao = new CategoriaDaoImpl();
        return notDao.setNonInviareCategoriaAgente(cf, categoriaNotifica);
    }

    public static UpdateCategoriaResponse setInviareCategoriaAdAgente(String cf, UpdateCategoriaNotificaDTO categoriaNotifica){
        CategoriaDao notDao = new CategoriaDaoImpl();
        return notDao.setInviareCategoriaAgente(cf, categoriaNotifica);
    }

    public static List<CategoriaNotifica> getCategorieDisattivateByCfAgente(String cf){
        CategoriaDao dao = new CategoriaDaoImpl();
        return dao.getCategorieDisattivateByCFAgente(cf);
    }

    public static List<CategoriaNotifica> getCategorieDisattivateByEmailCliente(String email){
        CategoriaDao dao = new CategoriaDaoImpl();
        return dao.getCategorieDisattivateByEmailCliente(email);
    }

    public static List<CategoriaNotifica> getAllCategorieByEmailCliente(String email){
        CategoriaDao dao = new CategoriaDaoImpl();
        return dao.getAllCategoriaByEmail(email);
    }

    public static List<CategoriaNotifica> getAllCategorieByCFAgente(String cf){
        CategoriaDao dao = new CategoriaDaoImpl();
        return dao.getAllCategoriaByCFAgente(cf);
    }





}
