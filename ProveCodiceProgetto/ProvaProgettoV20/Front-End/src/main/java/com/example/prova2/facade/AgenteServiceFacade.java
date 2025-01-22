package com.example.prova2.facade;
import com.example.prova2.dto.AgenteDatiDTO;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.Agente;
import com.example.prova2.service.AgenteService;

import java.io.IOException;


public class AgenteServiceFacade {


    public static boolean addAgente(Agente agente) {
        try {
            return AgenteService.addAgente(agente);
        }catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public static AgenteDatiDTO getAgenteByEmail(String email) {
        AgenteDatiDTO dto = AgenteService.getAgente(email);
        if (dto == null) {
            AlertFactory.generateFailAlertForErroreInterno();
        }
        return dto;
    }
}
