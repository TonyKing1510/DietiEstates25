package com.example.prova2.facade;
import com.example.prova2.dto.DatiDTO;
import com.example.prova2.factory.AlertFactory;
import com.example.prova2.model.Agente;
import com.example.prova2.model.GestoreAgenziaImmobiliare;
import com.example.prova2.service.AgenteService;
import com.example.prova2.service.NotificaService;
import javafx.application.Platform;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public class AgenteServiceFacade {


    public static boolean nonErrore = true;


    public static boolean addAgente(Agente agente, GestoreAgenziaImmobiliare g) {
        try {
            return AgenteService.addAgente(agente,g);
        }catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public static List<String> getFotoAgente(String cf){
        return AgenteService.getFotoAgente(cf);
    }


    public static Optional<Boolean> updateDatiAgente(Agente agente){
        CompletableFuture<Void> future=AgenteService.updateDatiAgente(agente)
                .thenAccept(response -> Platform.runLater(() -> {
                    if (response != null) {
                        gestisciCasoErrore(response.getError());
                        gestisciCasoPositivo(response.getError());
                    }else{
                        AlertFactory.generateFailAlertForErroreInterno();
                    }
                }));
        return Optional.of(future.isCompletedExceptionally() && nonErrore);
    }



    private static void gestisciCasoErrore(boolean error) {
        if (error) {
            AlertFactory.generateFailAlertForErroreInterno();
            nonErrore=false;
        }
    }

    private static void gestisciCasoPositivo(boolean error) {
        if (!error) {
            AlertFactory.generateSuccessAlertForSuccessUpdateDati();
        }
        nonErrore=false;
    }

}
