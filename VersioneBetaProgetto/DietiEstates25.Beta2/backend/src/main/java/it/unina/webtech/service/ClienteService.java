package it.unina.webtech.service;

import it.unina.webtech.dao.ClienteDao;
import it.unina.webtech.dao.ClienteDaoImpl;
import it.unina.webtech.dao.UserDao;
import it.unina.webtech.dao.UserDaoImpl;
import it.unina.webtech.dto.request.AddClienteRequestDTO;
import it.unina.webtech.dto.request.LasciaRecensioneRequestDTO;
import it.unina.webtech.dto.request.UpdateDatiRequestDTO;
import it.unina.webtech.dto.response.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ClienteService {

    private ClienteService(){}

    public static ClienteDTO addCliente(AddClienteRequestDTO cliente) {
        try {
            cliente.setPassword(SecurityPasswordService.hashPassword(cliente.getPassword()));
            ClienteDao dao = new ClienteDaoImpl();
            return dao.insert(cliente);
        } catch (SQLException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setErroreInterno(true);
            return clienteDTO;
        }
    }

    public static UpdateDatiResponseDTO updateDati(UpdateDatiRequestDTO nuoviDati,String emailAttuale) {
        try {
            UserDao dao = new UserDaoImpl();
            return dao.updateDatiCliente(nuoviDati,emailAttuale);
        } catch (SQLException e) {
            return null;
        }
    }

    public static ClienteDatiDTO getCliente(String email) {
        try {
            ClienteDao dao = new ClienteDaoImpl();
            return dao.getClienteByEmail(email);
        }catch (SQLException e){
            return null;
        }
    }

    public static boolean lasciaRecensione(LasciaRecensioneRequestDTO recensione){
        try {
            ClienteDao dao = new ClienteDaoImpl();
            return dao.lasciaRecensione(recensione);
        }catch (SQLException e){
            return false;
        }
    }

    public static List<GetAllRicercheResponse> getAllRicerche(String email){
        try {
            ClienteDao dao = new ClienteDaoImpl();
            return dao.getAllRicerche(email);
        }catch (SQLException e){
            return Collections.emptyList();
        }
    }

    public static AgenteDTO addCliente(String email, String password){
        try {
            UserDao dao = new UserDaoImpl();
            return dao.add(email, password);
        }catch (SQLException e){
            return null;
        }
    }
}
