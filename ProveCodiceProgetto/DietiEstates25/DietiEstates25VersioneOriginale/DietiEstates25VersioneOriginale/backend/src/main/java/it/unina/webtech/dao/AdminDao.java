package it.unina.webtech.dao;
import it.unina.webtech.dto.request.AddAdminRequestDTO;
import it.unina.webtech.dto.request.UpdatePasswordRequestDTO;

import java.sql.SQLException;

public interface AdminDao {
    boolean addAdmin(AddAdminRequestDTO admin) throws SQLException;

    boolean updatePassword(UpdatePasswordRequestDTO request) throws SQLException;
}
