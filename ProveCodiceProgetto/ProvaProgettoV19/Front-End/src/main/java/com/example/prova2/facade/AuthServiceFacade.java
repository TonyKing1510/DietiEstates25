package com.example.prova2.facade;
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
            if (gestisciCasoNonHaFattoPrimoAccesso(dto)) return null;
            return dto;
        } catch (IOException |  InterruptedException e) {
            AlertFactory.generateFailAlertForErroreConnessione();
            Thread.currentThread().interrupt();
            return null;
        }
    }

    private static boolean gestisciCasoNonHaFattoPrimoAccesso(GestoreAgenziaImmobiliareDTO dto) {
        if(!dto.isFattoPrimoAccesso()){
            AlertFactory.generateFailAlertForNonFattoPrimoAccesso();
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


    public static boolean primoLoginGestore(GestoreAgenziaImmobiliare g){
        GestoreAgenziaImmobiliareDTO dto= AuthService.effettuaPrimoLoginGestore(g);
        return gestisciErroreLoginPrimoAccesso(dto);
    }

    private static boolean gestisciErroreLoginPrimoAccesso(GestoreAgenziaImmobiliareDTO dto) {
        if(dto == null){
            AlertFactory.generateFailAlertForAccountNonTrovato();
            return false;
        }
        if(dto.isCredSbagliate()){
            AlertFactory.generateFailAlertForCredenzialiSbagliate();
            return false;
        }
        if (dto.isFattoPrimoAccesso()) {
            AlertFactory.generateFailAlertForPrimoAccesso();
            return false;
        }
        return true;
    }





}
