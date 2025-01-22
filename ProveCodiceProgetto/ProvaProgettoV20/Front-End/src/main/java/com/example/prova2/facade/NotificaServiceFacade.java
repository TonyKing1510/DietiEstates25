package com.example.prova2.facade;
import com.example.prova2.model.Notifica;
import com.example.prova2.service.NotificaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificaServiceFacade {
    public static List<Notifica> getNotificaAgente(String cf){
        try {
            return NotificaService.getNotificationsForAgente(cf);
        } catch (InterruptedException | IOException e) {
            return new ArrayList<>();
        }
    }
}
