package com.example.prova2.facade;
import com.example.prova2.dto.ClienteDTO;
import com.example.prova2.model.Cliente;
import com.example.prova2.service.ClienteService;

public class ClienteServiceFacade {
    public static ClienteDTO registrati(Cliente cliente){
        ClienteDTO dto=ClienteService.registrati(cliente);
        System.out.println(dto);
        return dto;
    }
}
