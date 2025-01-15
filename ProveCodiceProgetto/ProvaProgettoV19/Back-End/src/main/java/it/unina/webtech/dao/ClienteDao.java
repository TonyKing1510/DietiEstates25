package it.unina.webtech.dao;

import it.unina.webtech.dto.ClienteDTO;
import it.unina.webtech.model.Cliente;

public interface ClienteDao {
    public ClienteDTO insert(Cliente cliente);

    public boolean exists(Cliente cliente);
}
