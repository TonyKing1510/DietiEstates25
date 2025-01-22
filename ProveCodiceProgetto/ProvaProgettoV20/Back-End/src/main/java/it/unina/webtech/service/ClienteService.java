package it.unina.webtech.service;

import it.unina.webtech.dao.ClienteDao;
import it.unina.webtech.dao.ClienteDaoImpl;
import it.unina.webtech.dto.ClienteDTO;
import it.unina.webtech.model.Cliente;

import java.sql.SQLException;

public class ClienteService {
    public static ClienteDTO addCliente(Cliente cliente) {
        try {
            cliente.getAccountCliente().setPassword(SecurityPasswordService.hashPassword(cliente.getAccountCliente().getPassword()));
            ClienteDao dao = new ClienteDaoImpl();
            return dao.insert(cliente);
        } catch (SQLException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setErroreInterno(true);
            return clienteDTO;
        }
    }
}
