package it.unina.webtech.dao;


import it.unina.webtech.db.DatabaseConnection;
import it.unina.webtech.dto.GestoreAgenziaImmobiliareDTO;
import it.unina.webtech.model.AccountSemplice;
import it.unina.webtech.model.Agenzia;
import it.unina.webtech.model.GestoreAgenziaImmobiliare;
import it.unina.webtech.service.SecurityPasswordService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl() throws SQLException{
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(AccountSemplice user) {
        String sql = "INSERT INTO cliente (cf,email,password) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,"SVNGLN020002");
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore durante il salvataggio dell'utente: " + e.getMessage());
        }
    }

    @Override
    public boolean findAgenteByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select a.password from agente a where a.username = ?";
        return creaQuery(username, password, sql);
    }

    public boolean findClienteByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select a.password from cliente a where a.username = ?";
        return creaQuery(username, password, sql);
    }

    private boolean creaQuery(String username, String password, String sql) {
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return SecurityPasswordService.checkPassword(password,rs.getString("password"));
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public GestoreAgenziaImmobiliareDTO findGestoreByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select a.nomeagenzia,a.cf,a.password,a.admin,a.isfattoprimoaccesso from gestoreagenziaimmobiliare a where a.username = ?";
        return creaQueryPerGestore(username, password, sql);
    }


    private GestoreAgenziaImmobiliareDTO creaQueryPerGestore(String username, String password, String sql) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(risultatoOttenuto(rs) && isPasswordCorretta(password, rs)){
                if(isGestoreRecuperatounAdmin(rs)){
                        Agenzia a = new Agenzia(rs.getString("nomeagenzia"));
                        return new GestoreAgenziaImmobiliareDTO(null,true,false,rs.getString("cf"), a.getNomeAgenzia(),rs.getBoolean("isfattoprimoaccesso"));
                    }else{
                        Agenzia a = new Agenzia(rs.getString("nomeagenzia"));
                        String cf = rs.getString("cf");
                        return new GestoreAgenziaImmobiliareDTO(null,false,false,cf, a.getNomeAgenzia(),rs.getBoolean("isfattoprimoaccesso"));
                    }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean risultatoOttenuto(ResultSet rs) throws SQLException {
        return rs.next();
    }

    private static boolean isPasswordCorretta(String password, ResultSet rs) throws SQLException {
        return SecurityPasswordService.checkPassword(password, rs.getString("password"));
    }

    private static boolean isGestoreRecuperatounAdmin(ResultSet rs) throws SQLException {
        return rs.getBoolean("admin");
    }

}
