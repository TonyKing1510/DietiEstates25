package com.example.prova2.facade;
import com.example.prova2.dto.AgenteDTO;
import com.example.prova2.dto.GestoreAgenziaImmobiliareDTO;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.AccountSemplice;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AuthServiceFacade {

    private ObjectMapper objectMapper;


    public AuthServiceFacade() {
        this.objectMapper = new ObjectMapper();
    }

    public GestoreAgenziaImmobiliareDTO loginAdmin(String email, String password) {
        try {
            GestoreAgenziaImmobiliareDTO dto=AuthService.requestLoginAdmin(new AccountSemplice(email, password));
            if (gestisciCasoAccountNonTrovato(dto)) return null;
            if (gestisciCasoCredenzialiSbagliate(dto)) return null;
            return dto;
        } catch (IOException |  InterruptedException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreConnessione();
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public static boolean loginAgente(String email, String password) {
        try {
            AgenteDTO dto = AuthService.requestLoginAgente(new AccountSemplice(email, password));
            return gestisciDto(dto);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlertForErroreConnessione();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreConnessione();
            return false;
        }
    }

    public static boolean loginCliente(String email, String password) {
        try {
            AgenteDTO dto = AuthService.requestLoginCliente(new AccountSemplice(email, password));
            return gestisciDto(dto);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            AlertFactory.generateFailAlertForErroreConnessione();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.generateFailAlertForErroreConnessione();
            return false;
        }
    }

    private static boolean gestisciDto(AgenteDTO dto) {
        if(dto != null){
            if(dto.isCredenzialiSbagliate()){
                AlertFactory.generateFailAlertForCredenzialiSbagliate();return false;}
            if(dto.isErroreInterno()){
                AlertFactory.generateFailAlertForErroreInterno();return false;}
            return true;
        }
        return false;
    }


    private static boolean gestisciCasoCredenzialiSbagliate(GestoreAgenziaImmobiliareDTO dto) {
        if (dto.isCredSbagliate()) {
            AlertFactory.generateFailAlertForCredenzialiSbagliate();
            return true;
        }
        return false;
    }

    private static boolean gestisciCasoAccountNonTrovato(GestoreAgenziaImmobiliareDTO dto) {
        if (dto == null) {
            AlertFactory.generateFailAlertForAccountNonTrovato();
            return true;
        }
        return false;
    }







}
