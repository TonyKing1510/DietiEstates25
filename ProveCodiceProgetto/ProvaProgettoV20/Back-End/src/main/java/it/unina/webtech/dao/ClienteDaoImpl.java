package it.unina.webtech.dao;

import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.dto.ClienteDTO;
import it.unina.webtech.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDaoImpl implements ClienteDao {

    private Connection connection;

    public ClienteDaoImpl() throws SQLException {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ClienteDTO insert(Cliente cliente) {
        if(exists(cliente)) {
            return new ClienteDTO(true);
        }
        String sql = "INSERT INTO cliente (nome,cognome,telefono,password,email) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCognome());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4,cliente.getAccountCliente().getPassword());
            ps.setString(5,cliente.getAccountCliente().getEmail());
            int nRows=ps.executeUpdate();
            if(nRows > 0) {
                return new ClienteDTO(false);
            }
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setErroreInterno(true);
            return clienteDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setErroreInterno(true);
            return clienteDTO;
        }
    }

    @Override
    public boolean exists(Cliente cliente) {
        String sql = "SELECT 1 FROM (SELECT email FROM cliente UNION SELECT email FROM agente UNION SELECT email from gestoreagenziaimmobiliare) WHERE email=?";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,cliente.getAccountCliente().getEmail());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
