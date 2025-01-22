package com.example.prova2.facade;

import com.example.prova2.service.GestoreService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestoreServiceFacade {

    private GestoreServiceFacade(){}

    public static List<String> getCfGestore(){
        try {
            return GestoreService.getAllCfGestore();
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return new ArrayList<>();
        }
        catch (IOException e){
            return new ArrayList<>();
        }
    }

    public static List<String> getEmailGestore(){
        try {
            return GestoreService.getAllEmailGestore();
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return new ArrayList<>();
        }
        catch (IOException e){
            return new ArrayList<>();
        }
    }
}
